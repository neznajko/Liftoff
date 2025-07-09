////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
////////////////////////////////////////////////////////////////
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;
////////////////////////////////////////////////////////////////
import net.liftoff.Bounds;
////////////////////////////////////////////////////////////////
@Getter
@Setter
@SuperBuilder
public class Ball extends Bounds
{
    @Builder.Default private float r = 10;
    @Builder.Default private float xSpeed = 5;
    @Builder.Default private float ySpeed = 5;

    @Override
    public float top()
    {
        return y + r;
    }
    @Override
    public float ryte()
    {
        return x + r;
    }
    @Override
    public float bot()
    {
        return y - r;
    }
    @Override
    public float left()
    {
        return x - r;
    }
    public void update()
    {
        x += xSpeed;
        y += ySpeed;

        if( ryte() > Gdx.graphics.getWidth() || left() < 0 ){
            xSpeed = -xSpeed;
        }
        if( top() > Gdx.graphics.getHeight() || bot() < 0 ){
            ySpeed = -ySpeed;
        }
    }
    public void render( ShapeRenderer shape )
    {
        shape.setColor( color );
        shape.circle( x, y, r );
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
