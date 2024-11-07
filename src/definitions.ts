
export interface CropImageOptions {
  uri: string;
  aspectRatio: string;
}

export interface CropImageState {
  result: string;
}

export interface CapacitorCropperPlugin {
  crop(options: CropImageOptions): Promise<CropImageState>;
}
