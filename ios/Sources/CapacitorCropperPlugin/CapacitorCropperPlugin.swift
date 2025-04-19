import Foundation
import Capacitor
import Mantis
/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorCropperPlugin)
public class CapacitorCropperPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "CapacitorCropperPlugin"
    public let jsName = "CapacitorCropper"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "crop", returnType: CAPPluginReturnPromise)
    ]
    private var call: CAPPluginCall?
    
    @objc func crop(_ call: CAPPluginCall) {
        do {
            let value = call.getString("uri") ?? ""
            self.call = call
            let index = value.index(value.startIndex, offsetBy: 7); // index with an offset of 6 characters
            let str = value[index...];
            let urlPath = String(str);
            let url = URL(fileURLWithPath: urlPath);
            let imageData:NSData = try NSData(contentsOf: url)
            let image = UIImage(data: imageData as Data)
            let cropViewController = Mantis.cropViewController(image: image!);
            DispatchQueue.main.async {
                cropViewController.delegate = self;
                self.bridge?.viewController?.present(cropViewController, animated: true, completion: nil)
            }
        } catch {
            // print("Error loading image : \(error)")
        }
    }
}

extension CapacitorCropperPlugin: CropViewControllerDelegate {
    public func cropViewControllerDidCrop(_ cropViewController: Mantis.CropViewController, cropped: UIImage, transformation: Mantis.Transformation, cropInfo: Mantis.CropInfo) {
        let imageData = cropped.pngData()
        let base64 = imageData?.base64EncodedString(options: .lineLength64Characters)
        self.call?.resolve([
            "result": base64
        ])
        cropViewController.dismiss(animated: true)
    }
    
    public func cropViewControllerDidCancel(_ cropViewController: Mantis.CropViewController, original: UIImage) {
        cropViewController.dismiss(animated: true)
    }
    
    
}

