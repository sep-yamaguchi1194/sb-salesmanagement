package sep.salesmanagement.yt.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;


public class PageWrapper<T> {

    public static final int ITEM_RANGE = 5;
    public static final int ITEM_GAP = 2;
    private Page<T> page;
    private List<PageItem> items;
    private int currentNumber;
    private String url;
    private String customer;
    private String status;
    private String orderName;

    public PageWrapper(Page<T> page, String url) {
        this.page = page;
        this.url = url;
        items = new ArrayList<PageItem>();

        currentNumber = page.getNumber() + 1;
        int start, size;
        if(page.getTotalPages() <= ITEM_RANGE) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if(currentNumber <= ITEM_RANGE - ITEM_GAP) {
                start = 1;
                size = ITEM_RANGE;
            } else if(currentNumber >= page.getTotalPages() - ITEM_GAP) {
                start = page.getTotalPages() - ITEM_RANGE + 1;
                size = ITEM_RANGE;
            } else {
                start = currentNumber - ITEM_GAP;
                size = ITEM_RANGE;
            }
        }

        for(int i = 0; i < size; i++) {
            items.add(new PageItem(start+i, (start+i) == currentNumber));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<PageItem> getItems() {
        return items;
    }

    public int getNumber() {
        return currentNumber;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public boolean isHasPreviousPage() {
        return page.hasPrevious();
    }

    public boolean isHasNextPage() {
        return page.hasNext();
    }

    public class PageItem {
        private int number;
        private boolean current;
        public PageItem(int number, boolean current) {
            this.number = number;
            this.current = current;
        }

        public int getNumber() {
            return this.number;
        }

        public boolean isCurrent() {
            return this.current;
        }
    }
}
