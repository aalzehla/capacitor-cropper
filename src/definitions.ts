export interface CapacitorCropperPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
