import java.awt.event.KeyEvent;
import java.util.Observable;

public class GameEvents extends Observable
{
    public Object event;

    public void keyP(KeyEvent e)
    {
        event = e;
        setChanged();
        notifyObservers(this);
    }

}