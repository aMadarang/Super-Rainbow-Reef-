import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controls extends KeyAdapter
{
    private GameEvents events;

    public Controls(GameEvents events)
    {
        this.events = events;
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        events.keyP(e);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        events.keyP(e);
    }
}