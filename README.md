# @aalzehla/capacitor-cropper

Image Cropper for Capacitor

## Install

```bash
npm install @aalzehla/capacitor-cropper
npx cap sync
```

## API

<docgen-index>

* [`crop(...)`](#crop)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### crop(...)

```typescript
crop(options: CropImageOptions) => Promise<CropImageState>
```

| Param         | Type                                                          |
| ------------- | ------------------------------------------------------------- |
| **`options`** | <code><a href="#cropimageoptions">CropImageOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#cropimagestate">CropImageState</a>&gt;</code>

--------------------


### Interfaces


#### CropImageState

| Prop         | Type                |
| ------------ | ------------------- |
| **`result`** | <code>string</code> |


#### CropImageOptions

| Prop          | Type                 |
| ------------- | -------------------- |
| **`uri`**     | <code>string</code>  |
| **`x`**       | <code>number</code>  |
| **`y`**       | <code>number</code>  |
| **`rounded`** | <code>boolean</code> |
| **`title`**   | <code>string</code>  |

</docgen-api>
