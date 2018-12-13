import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class Shell extends Collide implements Observer {

    private final int left, right;
    private boolean LP, RP;

    public Shell(Image img, int x, int y, int leftKey, int rightKey)
    {
        super(img, x, y);
        this.left = leftKey;
        this.right = rightKey;
        this.LP =  false;
        this.RP = false;
    }

    public void move()
    {
        if(this.LP)
        {
            this.x -= 9;

            if(x < 0)
            {
                x = 0;
            }
            if(x >= 560)
            {
                x = 560;
            }
        }

        if(this.RP)
        {
            this.x += 9;

            if(x < 0)
            {
                x = 0;
            }
            if(x >= 560)
            {
                x = 560;
            }
        }
    }

    @Override
    public void update(Observable obj, Object arg)
    {
        GameEvents ge = (GameEvents) arg;
        KeyEvent e = (KeyEvent) ge.event;

        //Left button
        if(e.getKeyCode() == left)
        {
            if(e.getID() == KeyEvent.KEY_RELEASED)
            {
                this.LP  = false;
            } else if (e.getID() == KeyEvent.KEY_PRESSED)
            {
                this.LP = true;
            }
        }

        //Right Button
        if(e.getKeyCode() == right){
            if(e.getID() == KeyEvent.KEY_RELEASED)
            {
                this.RP = false;
            }else if (e.getID() == KeyEvent.KEY_PRESSED)
            {
                this.RP = true;
            }
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 80, 30);
    }
}