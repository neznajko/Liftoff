////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
////////////////////////////////////////////////////////////////
import lombok.Getter;
import lombok.Setter;
////////////////////////////////////////////////////////////////
import net.liftoff.Bounds;
////////////////////////////////////////////////////////////////
@Getter
@Setter
public class Ball extends Bounds
{
    private static final float R = 12;
    private static final float VX = -5;
    private static final float VY = 4;

    private float r;
    private float vx;
    private float vy;
    private float speed;
    
    private Ball( Builder builder )
    {
        super( builder.x, builder.y, builder.color );
        r = builder.r;
        vx = builder.vx;
        vy = builder.vy;
        speed = Vector2.len( vx, vy );
    }
    public static class Builder
    {
        private float x;
        private float y;
        private Color color;

        private float r = R;
        private float vx = VX;
        private float vy = VY;

        public Builder( float x, float y, Color color )
        {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        public Builder radius( float r )
        {
            this.r = r;
            return this;
        }
        public Builder velocity( float vx, float vy )
        {
            this.vx = vx;
            this.vy = vy;
            return this;
        }
        public Ball build()
        {
            return new Ball( this );
        }
    }
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
        x += vx;
        y += vy;

        if( unbound_width() ){ vx = -vx; }
        if( unbound_height()){ vy = -vy; }
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
