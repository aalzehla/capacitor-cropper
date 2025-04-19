import { CapacitorCropper } from '@aalzehla/capacitor-cropper';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    CapacitorCropper.echo({ value: inputValue })
}
