import { WebPlugin } from '@capacitor/core';

import type { CapacitorCropperPlugin, CropImageOptions, CropImageState } from './definitions';

export class CapacitorCropperWeb extends WebPlugin implements CapacitorCropperPlugin {
  async crop(options: CropImageOptions): Promise<CropImageState> {
    console.log('ECHO', options);
    return { result: options.uri};
  }
}
