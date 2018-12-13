import java.awt.*;

public class Star extends Collide
{
    private double Vspeed;
    private double Hspeed;

    public Star(Image img, int x, int y)
    {
        super(img, x, y);
        Vspeed = 5; //x
        Hspeed = 3; //y

        //rectangle boundary
        w = img.getWidth(null);
        h = img.getHeight(null);
    }

    public void update()
    {
        //Gravity; slightly increase fall speed
        Vspeed += .1; // gravity

        x += (int)Math.round(Hspeed);
        y += (int)Math.round(Vspeed);

        //same as tank
        if(y < 0)
        {
            Vspeed = -Vspeed;
        }

        if(x < 0)
        {
            Hspeed = -Hspeed;
        }

        if(x > ReefWorld.sWidth - 35)
        {
            Hspeed = -Hspeed;
        }

        if(y > ReefWorld.sHeight - 35)
        {
            Vspeed = -Vspeed;
        }

    }

    public void setVerticalSpeed(double verticalSpeed)
    {
        Vspeed = verticalSpeed;
        Vspeed -= 0.4; // increase the speed for each hit
    }

    public double getVSpeed()
    {
        return Vspeed;
    }

    //use for collisionDetection
    public Rectangle getRect()
    {
        return new Rectangle(x, y, getWidth(), getHeight());
    }
}
