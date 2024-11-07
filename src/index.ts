import { registerPlugin } from '@capacitor/core';

import type { CapacitorCropperPlugin } from './definitions';

const CapacitorCropper = registerPlugin<CapacitorCropperPlugin>('CapacitorCropper', {
  web: () => import('./web').then((m) => new m.CapacitorCropperWeb()),
});

export * from './definitions';
export { CapacitorCropper };
