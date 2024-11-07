
export interface CropImageOptions {
  uri: string;
  x?: number;
  y?: number;
  rounded?: boolean;
  title?: string;
}

export interface CropImageState {
  result: string;
}

export interface CapacitorCropperPlugin {
  crop(options: CropImageOptions): Promise<CropImageState>;
}
