package dnd.com.dndaw;

/**
 * Created by jonathanolson on 8/6/15.
 */
public class Legion
{
    private String title;
    private String link;

    public Legion(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public Legion() {
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

}
