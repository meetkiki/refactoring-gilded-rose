package com.gildedrose;

import java.util.Arrays;

class GildedRose {
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
        if (item.nameNotEquals("Aged Brie")
                && item.nameNotEquals("Backstage passes to a TAFKAL80ETC concert")) {
            sellInMinusOneIfNotSulfuras(item);
            return this;
        }
        AutoIncrementQualityLessThanFifty(item);
        if (item.nameEquals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn < 11) {
                AutoIncrementQualityLessThanFifty(item);
            }

            if (item.sellIn < 6) {
                AutoIncrementQualityLessThanFifty(item);
            }
        }
        return this;
    }

    private void AutoIncrementQualityLessThanFifty(Product item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private GildedRose expiredHandle(Product item) {
        if (item.sellIn < 0) {
            if (item.nameNotEquals("Aged Brie")) {
                if (item.nameNotEquals("Backstage passes to a TAFKAL80ETC concert")) {
                    sellInMinusOneIfNotSulfuras(item);
                } else {
                    item.quality = 0;
                }
            } else {
                AutoIncrementQualityLessThanFifty(item);
            }
        }
        return this;
    }

    private GildedRose qualityMinusOneIfNotSulfuras(Product item) {
        if (item.nameNotEquals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
        return this;
    }

    private GildedRose sellInMinusOneIfNotSulfuras(Product item) {
        if (item.quality > 0 && item.nameNotEquals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
        return this;
    }
}
