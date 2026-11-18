////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
////////////////////////////////////////////////////////////////
import net.liftoff.Bounds;
////////////////////////////////////////////////////////////////
public class Ball extends Bounds
{
    private static final float R = 12;
    private static final float VX = -5;
    private static final float VY = 4;

    private float r;
    private Vector2 v;
    
    private Ball( Builder builder )
    {
        super( builder.x, builder.y, builder.color );
        r = builder.r;
        v = new Vector2( builder.vx, builder.vy );
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
        x += v.x;
        y += v.y;

        if( unbound_width() ){ v.x = -v.x; }
        if( unbound_height()){ v.y = -v.y; }
    }
    public void render( ShapeRenderer shape )
    {
        shape.setColor( color );
        shape.circle( x, y, r );
    }
    void bounceOnVaus( Side side, Vaus vaus )
    {
        switch( side ){
        case TOP:
            y = vaus.top() + r;
            float a = 90f - vaus.hitOffset( this )*60f;
            v.setAngleDeg( a );
            break;
        case BOT:
            y = vaus.bot() - r;
            v.y = -v.y;
            break;
        case LEFT:
            x = vaus.left() - r;
            v.x = -v.x;
            break;
        case RYTE:
            x = vaus.ryte() + r;
            v.x = -v.x;
            break;
        }
    }
}
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
