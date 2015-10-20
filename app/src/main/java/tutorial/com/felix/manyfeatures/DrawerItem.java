package tutorial.com.felix.manyfeatures;

/**
 * Created by felix on 10/20/2015.
 */
public class DrawerItem {
    private int iconId;
    private String title;
    private String subTitle;

    public DrawerItem() {
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
