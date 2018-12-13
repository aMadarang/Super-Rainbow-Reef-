import java.awt.*;

abstract public class Collide
{
    protected int x, y;
    protected int w, h;
    protected Image img;

    public Collide(Image img, int x, int y)
    {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.w;
    }

    public int getHeight()
    {
        return this.h;
    }

    public Image getImg()
    {
        return this.img;
    }
}