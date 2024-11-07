
export interface CropImageOptions {
  uri: string;
  aspectRatio: string;
  x: number;
  y: number;
}

export interface CropImageState {
  result: string;
}

export interface CapacitorCropperPlugin {
  crop(options: CropImageOptions): Promise<CropImageState>;
}
