import { CapacitorCropper } from '@aalzehla&#x2F;capacitor-cropper';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    CapacitorCropper.echo({ value: inputValue })
}
