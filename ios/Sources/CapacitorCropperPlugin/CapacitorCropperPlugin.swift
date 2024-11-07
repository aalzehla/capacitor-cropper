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
        let value = call.getString("uri") ?? ""
        self.call = call
        let dataDecoded: Data = Data(base64Encoded: value, options: NSData.Base64DecodingOptions(rawValue: 0))!
        let decodedimage:UIImage = UIImage(data: dataDecoded)!
        let cropViewController = Mantis.cropViewController(image: decodedimage);
        cropViewController.delegate = self
        bridge?.viewController?.present(cropViewController, animated: true, completion: nil)
        
    }
}

extension CapacitorCropperPlugin: CropViewControllerDelegate {
    public func cropViewControllerDidCrop(_ cropViewController: Mantis.CropViewController, cropped: UIImage, transformation: Mantis.Transformation, cropInfo: Mantis.CropInfo) {
        let imageData = cropped.pngData()
        let base64 = imageData?.base64EncodedString(options: .lineLength64Characters)
        self.call?.resolve([
            "result": base64
        ])
    }
    
    public func cropViewControllerDidCancel(_ cropViewController: Mantis.CropViewController, original: UIImage) {
        
    }
    
    
}

