import { WebPlugin } from '@capacitor/core';

import type { CapacitorCropperPlugin } from './definitions';

export class CapacitorCropperWeb extends WebPlugin implements CapacitorCropperPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
