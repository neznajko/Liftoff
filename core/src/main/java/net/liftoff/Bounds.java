////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
////////////////////////////////////////////////////////////////
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
////////////////////////////////////////////////////////////////
@Getter
@Setter
@SuperBuilder
public abstract class Bounds
{
    @Builder.Default protected float x = 10;
    @Builder.Default protected float y = 10;
    @Builder.Default protected Color color = Color.RED;

    public abstract float left();
    public abstract float ryte();
    public abstract float top();
    public abstract float bot();

    public enum Side { LEFT, RYTE, TOP, BOT, NONE }

    public boolean cross( Bounds other )
    {
        return( other.left() < ryte() &&
                other.ryte() > left() &&
                other.bot() < top() &&
                other.top() > bot());
    }

    public Side crossbar( Bounds other ){
        if( cross( other ) == false ) return Side.NONE;
        float top = top() - other.bot();
        float bot = other.top() - bot();
        float ryte = ryte() - other.left();
        float left = other.ryte() - left();
        if( top < bot ){
            if( left < ryte ){
                // TOP LEFT
                return ( top < left ) ? Side.TOP : Side.LEFT;
            } else {
                // TOP RYTE
                return ( top < ryte ) ? Side.TOP : Side.RYTE;
            }
        } else {
            if( left < ryte ){
                // BOT LEFT
                return ( bot < left ) ? Side.BOT : Side.LEFT;
            } else {
                // BOT RYTE
                return ( bot < ryte ) ? Side.BOT : Side.RYTE;
            }
        }
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
