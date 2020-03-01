package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT
            = "Backstage passes to a TAFKAL80ETC concert";


    Product[] items;

    public GildedRose(Product[] items) {
        this.items = items;
    }

    public void update_quality() {
        Arrays.stream(items).forEach(item -> updateItemQuality(item)
                        .qualityMinusOneIfNotSulfuras(item)
                        .expiredHandle(item));
    }

    private GildedRose updateItemQuality(Product item) {
        if (item.nameNotEquals(AGED_BRIE)
                && item.nameNotEquals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
            sellInMinusOneIfNotSulfuras(item);
            return this;
        }
        item.autoIncrementQualityLessThanFifty();
        if (item.nameEquals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
            if (item.sellIn < 11) {
                item.autoIncrementQualityLessThanFifty();
            }

            if (item.sellIn < 6) {
                item.autoIncrementQualityLessThanFifty();
            }
        }
        return this;
    }


    private GildedRose expiredHandle(Product item) {
        if (item.sellIn >= 0) {
            return this;
        }
        if (item.nameNotEquals(AGED_BRIE)) {
            if (item.nameNotEquals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
                sellInMinusOneIfNotSulfuras(item);
            } else {
                item.quality = 0;
            }
        } else {
            item.autoIncrementQualityLessThanFifty();
        }
        return this;
    }

    private GildedRose qualityMinusOneIfNotSulfuras(Product item) {
        if (item.nameNotEquals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.sellIn = item.sellIn - 1;
        }
        return this;
    }

    private GildedRose sellInMinusOneIfNotSulfuras(Product item) {
        if (item.quality > 0 && item.nameNotEquals(SULFURAS_HAND_OF_RAGNAROS)) {
            item.quality = item.quality - 1;
        }
        return this;
    }
}
