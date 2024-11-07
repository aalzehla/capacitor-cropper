
export interface CropImageOptions {
  uri: string;
  aspectRatio: string;
}

export interface CropImageState {
  uri: string;
}

export interface CapacitorCropperPlugin {
  crop(options: CropImageOptions): Promise<CropImageState>;
}
