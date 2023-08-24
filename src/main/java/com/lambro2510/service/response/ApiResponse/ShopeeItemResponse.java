package com.lambro2510.service.response.ApiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShopeeItemResponse  {
  private String error;
  private String errorMsg;
  private ItemData data;

  @Data
  public static class ItemData {
    private List<Feed> feeds;
    private Integer feedTotal;
  }

  @Data
  public static class Feed {
    private String type;
    @JsonProperty("item_card")
    private ItemCard itemCard;
    private AdsItemCard adsItemCard;
    private ShopeePayVoucherCard shopeePayVoucherCard;
    private FoodItemCard foodItemCard;
    private TopProductCard topProductCard;
    private FeaturedCollectionCard featuredCollectionCard;
    private LivestreamCard livestreamCard;
    private VideoCard videoCard;
    private SearchCard searchCard;
    private FoodCollectionCard foodCollectionCard;
    private ImageSearchCard imageSearchCard;
    private InsuranceCard insuranceCard;
    private FashionCard fashionCard;
  }

  @Data
  public static class ItemCard {
    private Item item;
    private ItemCardLabelData itemCardLabelData;
    private Object uiStyle;
    private RuntimeParserMeta runtimeParserMeta;
  }

  @Data
  public static class Item {
    private Long itemid;
    private Long shopid;
    private String name;
    private List<Integer> labelIds;
    private String image;
    private List<String> images;
    private Integer stock;
    private Integer sold;
    private Integer historicalSold;
    private Integer likedCount;
    private Long catid;
    private Long price;
    private Long priceMin;
    private Object hiddenPriceDisplay;
    private Integer showDiscount;
    private Integer rawDiscount;
    private String discount;
    private Object videoInfoList;
    private ItemRating itemRating;
    private Integer itemType;
    private String referenceItemId;
    private Boolean isAdult;
    private Integer badgeIconType;
    private Boolean shopeeVerified;
    private Boolean isOfficialShop;
    private Boolean showFreeShipping;
    private Object exclusivePriceInfo;
    private Object isGroupBuyItem;
    private Object welcomePackageInfo;
    private Object addOnDealInfo;
    private Boolean isPreferredPlusSeller;
    private String shopLocation;
    private VoucherInfo voucherInfo;
    private Boolean isOnFlashSale;
    private Object splInstallmentTenure;
    private Object isLiveStreamingPrice;
    private String shopName;
    private Boolean isMart;
    private Object autogenTitle;
    private Object autogenTitleId;
    private Boolean isServiceByShopee;
    private Object overlayImageIndex;
    private Object overlayImages;
    private Integer flashSaleStock;
    private Integer globalSoldCount;
    private Integer displaySoldCountSource;
    private Object freeShippingInfo;
    private String info;
    private String dataType;
    private String key;
    private Object count;
    private Object adsid;
    private Object campaignid;
    private Object deductionInfo;
    private DeepDiscountSkin deepDiscountSkin;
    private ExperimentInfo experimentInfo;
    private Object relationshipLabel;
    private Object newUserLabel;
    private Object wpEligibility;
    private Object platformVoucher;
    private Object highlightVideo;
    private Object searchId;
    private Boolean canUseCod;
    private Object pubId;
    private String pubContextId;
    private Object friendRelationshipLabel;
    private Object productBanners;
    private Object topProductLabel;
    private Object redirectInfo;
    private Object redirectType;
    private Object customizedOverlayType;
    private Object customizedOverlayInfo;
    private Object mainDisplayingType;
    private Object videoInfo;
  }

  @Data
  public static class ItemRating {
    private Double ratingStar;
    private List<Integer> ratingCount;
    private Integer rcountWithContext;
    private Integer rcountWithImage;
  }

  @Data
  public static class VoucherInfo {
    private Long promotionId;
    private String voucherCode;
    private String label;
  }

  @Data
  public static class DeepDiscountSkin {
    private Integer skinId;
    private Long startTime;
    private Long endTime;
    private SkinData skinData;
  }

  @Data
  public static class SkinData {
    private PromoLabel promoLabel;
  }

  @Data
  public static class PromoLabel {
    private String promotionPrice;
    private String hiddenPromotionPrice;
    private String text;
    private Long startTime;
    private Long endTime;
  }

  @Data
  public static class ExperimentInfo {
    private TitleAutogenExp titleAutogenExp;
    private Object highlightVideoExp;
    private FlashSaleLabelExp flashSaleLabelExp;
    private Object cardUiStyleExp;
  }

  @Data
  public static class TitleAutogenExp {
    private String group;
    private Object title;
  }

  @Data
  public static class FlashSaleLabelExp {
    private String group;
    private Integer itemStatus;
  }

  @Data
  public static class ItemCardLabelData {
    private List<ImageFlag> imageFlag;
    private List<OverlayImage> overlayImage;
    private List<PromotionLabel> promotionLabel;
    private List<ExtraEligibleImageFlag> extraEligibleImageFlag;
    private List<ExtraEligibleOverlayImage> extraEligibleOverlayImage;
    private List<ExtraEligiblePromotionLabel> extraEligiblePromotionLabel;
  }

  @Data
  public static class ImageFlag {
    private Integer type;
    private ImageFlagData data;
  }

  @Data
  public static class ImageFlagData {
    private String name;
    private String image;
    private Integer imageWidth;
    private Integer imageHeight;
  }

  @Data
  public static class OverlayImage {
    private Integer type;
    private OverlayImageData data;
  }

  @Data
  public static class OverlayImageData {
    private String name;
    private String image;
    private Integer imageWidth;
    private Integer imageHeight;
  }

  @Data
  public static class PromotionLabel {
    private Integer type;
    private PromotionLabelData data;
    private Object tracking;
  }

  @Data
  public static class PromotionLabelData {
    private String name;
    private String text;
    private String textColor;
    private Object image;
    private Object imageWidth;
    private Object imageHeight;
  }

  @Data
  public static class ExtraEligibleImageFlag {
    private Integer type;
    private ExtraEligibleImageFlagData data;
  }

  @Data
  public static class ExtraEligibleImageFlagData {
    private String name;
    private String image;
    private Integer imageWidth;
    private Integer imageHeight;
  }

  @Data
  public static class ExtraEligibleOverlayImage {
    private Integer type;
    private ExtraEligibleOverlayImageData data;
  }

  @Data
  public static class ExtraEligibleOverlayImageData {
    private String name;
    private String image;
    private Integer imageWidth;
    private Integer imageHeight;
  }

  @Data
  public static class ExtraEligiblePromotionLabel {
    private Integer type;
    private ExtraEligiblePromotionLabelData data;
  }

  @Data
  public static class ExtraEligiblePromotionLabelData {
    private String name;
    private String text;
    private String textColor;
    private Object image;
    private Object imageWidth;
    private Object imageHeight;
  }

  @Data
  public static class RuntimeParserMeta {
    private Object featureComponentId;
    private Boolean useClientSideDataTransform;
    private String handlerName;
  }

  @Data
  public static class AdsItemCard {
    // Define fields here for AdsItemCard if needed
  }

  @Data
  public static class ShopeePayVoucherCard {
    // Define fields here for ShopeePayVoucherCard if needed
  }

  @Data
  public static class FoodItemCard {
    // Define fields here for FoodItemCard if needed
  }

  @Data
  public static class TopProductCard {
    // Define fields here for TopProductCard if needed
  }

  @Data
  public static class FeaturedCollectionCard {
    // Define fields here for FeaturedCollectionCard if needed
  }

  @Data
  public static class LivestreamCard {
    // Define fields here for LivestreamCard if needed
  }

  @Data
  public static class VideoCard {
    // Define fields here for VideoCard if needed
  }

  @Data
  public static class SearchCard {
    // Define fields here for SearchCard if needed
  }

  @Data
  public static class FoodCollectionCard {
    // Define fields here for FoodCollectionCard if needed
  }

  @Data
  public static class ImageSearchCard {
    // Define fields here for ImageSearchCard if needed
  }

  @Data
  public static class InsuranceCard {
    // Define fields here for InsuranceCard if needed
  }

  @Data
  public static class FashionCard {
    // Define fields here for FashionCard if needed
  }
}
