////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
////////////////////////////////////////////////////////////////
import lombok.Getter;
import lombok.Setter;
////////////////////////////////////////////////////////////////
@Getter
@Setter
public abstract class Bounds
{
    protected float x;
    protected float y;
    protected Color color;

    public Bounds( float x, float y, Color color )
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public abstract float left();
    public abstract float ryte();
    public abstract float top();
    public abstract float bot();

    public enum Side { LEFT, RYTE, TOP, BOT, NONE }

    public boolean cross( Bounds other )
    {
        return( other.left() < ryte() &&
                other.ryte() > left() &&
                other.bot()  < top()  &&
                other.top()  > bot());
    }
    public Side crossbar( Bounds other )
    {
        if( cross( other ) == false ) return Side.NONE;
        float top = top() - other.bot();
        float bot = other.top() - bot();
        float ryte = ryte() - other.left();
        float left = other.ryte() - left();
        if( top < bot ){
            if( left < ryte ){
                return ( top < left ) ? Side.TOP : Side.LEFT;
            } else {
                return ( top < ryte ) ? Side.TOP : Side.RYTE;
            }
        } else {
            if( left < ryte ){
                return ( bot < left ) ? Side.BOT : Side.LEFT;
            } else {
                return ( bot < ryte ) ? Side.BOT : Side.RYTE;
            }
        }
    }
    public boolean unbound_width()
    {
        return ryte() > Gdx.graphics.getWidth() || left() < 0;
    }
    public boolean unbound_height()
    {
        return top() > Gdx.graphics.getHeight() || bot() < 0;
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
