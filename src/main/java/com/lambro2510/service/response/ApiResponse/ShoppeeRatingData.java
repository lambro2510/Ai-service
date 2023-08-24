package com.lambro2510.service.response.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShoppeeRatingData {
  private RatingData data;
  private int error;
  private RatingData errorMsg;

  @Data
  public static class RatingData {
    private List<Rating> ratings;
    private boolean isSipItem;
    private String rcmdAlgo;
    private boolean downgradeSwitch;
    private boolean hasMore;
    private boolean showLocalReview;
    private String browsingUi;
    private boolean enableBuyerGalleryMedia;
    private Object userLatestRating;
    private String sizeInfoAbt;
    private List<Object> topRatings;
    private boolean resizeImageAbt;
    private String purchaseBarAbt;
    private List<Object> tagFilters;
    private String signature;

    @Data
    public static class Rating {
      private long orderid;
      private long itemid;
      private long cmtid;
      private long ctime;
      private int rating;
      private long userid;
      private long shopid;
      private String comment;
      @JsonProperty("rating_star")
      private Integer ratingStar;
      private int status;
      private long mtime;
      private int editable;
      private int opt;
      private int filter;
      private List<Object> mentioned;
      private boolean isHidden;
      private Object canFollowUp;
      private Object followUp;
      private String authorUsername;
      private String authorPortrait;
      private long authorShopid;
      private boolean anonymous;
      private List<String> images;
      private List<Video> videos;
      private List<ProductItem> productItems;
      private Object deleteReason;
      private Object deleteOperator;
      private ItemRatingReply itemRatingReply;
      private Object tags;
      private long editableDate;
      private Object showReply;
      private int likeCount;
      private Object liked;
      private boolean syncToSocial;
      private DetailedRating detailedRating;
      private boolean excludeScoringDueLowLogistic;
      private Object loyaltyInfo;
      private List<String> templateTags;
      private boolean hasTemplateTag;
      private Object syncToSocialToggle;
      private SipInfo sipInfo;
      private boolean isRepeatedPurchase;
      private boolean displayVariationFilter;
      private int overallFit;
      private boolean isNormalItem;
      private Object viewed;
      private Object showView;
      private Object syncToSocialDetail;
      private Object profile;
      private Object sizeInfoTags;
      private String sizeInfoAbt;
      private List<ImageData> imageData;
      private KeyMedia keyMedia;
      private boolean isSuperReview;
      private List<Object> superReviewerTag;
      private Object isNewlyCreated;
      private Object templateHints;
      private Object templateTagsHints;
      private String region;
      private OriginalItemInfo originalItemInfo;
      private Object templateAbt;
      private Object isRepeatEdited;
      private boolean showSuperReviewTag;
      private int superReviewStatus;
    }

    @Data
    public static class Video {
      private String id;
      private String cover;
      private String url;
      private int duration;
      private Object uploadTime;
      private String mmsExt;
      private String coverImageId;
      private Highlight highlight;
    }

    @Data
    public static class Highlight {
      private String id;
      private String cover;
      private String url;
      private Object duration;
      private Object uploadTime;
      private String mmsExt;
      private DefaultFormat defaultFormat;
      private ExtendFormats extendFormats;
      private String abTest;
    }

    @Data
    public static class DefaultFormat {
      private int format;
      private String defn;
      private String profile;
      private String path;
      private String url;
      private int width;
      private int height;
      private int bitrate;
      private int size;
      private double fps;
      private int duration;
      private long updateTime;
    }

    @Data
    public static class ExtendFormats {
      private List<H265Format> h265;
    }

    @Data
    public static class H265Format {
      private int format;
      private String defn;
      private String profile;
      private String path;
      private String url;
      private int width;
      private int height;
      private int bitrate;
      private int size;
      private double fps;
      private int duration;
      private long updateTime;
    }

    @Data
    public static class ProductItem {
      private long itemid;
      private long shopid;
      private String name;
      private String image;
      private int isSnapshot;
      private long snapshotid;
      private long modelid;
      private String modelName;
      private List<String> options;
    }

    @Data
    public static class ItemRatingReply {
      private long orderid;
      private Object itemid;
      private long cmtid;
      private long ctime;
      private Object rating;
      private long userid;
      private Object shopid;
      private String comment;
      private Object ratingStar;
      private Object status;
      private long mtime;
      private Object editable;
      private Object opt;
      private Object filter;
      private Object mentioned;
      private boolean isHidden;
    }

    @Data
    public static class DetailedRating {
      private int productQuality;
      private int sellerService;
      private int deliveryService;
    }

    @Data
    public static class SipInfo {
      private boolean isOversea;
      private String originRegion;
    }

    @Data
    public static class ImageData {
      private String imageId;
      private String coverImageId;
    }

    @Data
    public static class KeyMedia {
      private int keyMediaCode;
      private String keyMediaId;
    }

    @Data
    public static class OriginalItemInfo {
      private long itemid;
      private long shopid;
      private String name;
      private String image;
    }
  }
}
