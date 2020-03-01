package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void update_quality() {
        for (int i = 0; i < items.length; i++) {
            updateItemQuality(items[i]);

            qualityMinusOneIfNotSulfuras(items[i]);

            expiredHandle(items[i]);
        }
    }

    private void updateItemQuality(Item item) {
        if (!item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                qualityMinusOneIfNotSulfuras(item);
            }
        } else {
            AutoIncrementQualityLessThanFifty(item);
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.sell_in < 11) {
                    AutoIncrementQualityLessThanFifty(item);
                }

                if (item.sell_in < 6) {
                    AutoIncrementQualityLessThanFifty(item);
                }
            }
        }
    }

    private void AutoIncrementQualityLessThanFifty(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private Item expiredHandle(Item item) {
        if (item.sell_in < 0) {
            if (!item.name.equals("Aged Brie")) {
                if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (item.quality > 0) {
                        qualityMinusOneIfNotSulfuras(item);
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                AutoIncrementQualityLessThanFifty(item);
            }
        }
        return item;
    }

    private Item qualityMinusOneIfNotSulfuras(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sell_in = item.sell_in - 1;
        }
        return item;
    }
}
