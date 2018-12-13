import java.awt.*;

public class Brick extends Collide
{
    private int points;

    public Brick(Image img, int x, int y, int score)
    {
        super(img, x, y);
        this.points = score;
        w = img.getWidth(null);
        h = img.getHeight(null);
    }

    //update points when brick hit
    public int getPoints()
    {
        return points;
    }

    public Rectangle getRect()
    {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
}