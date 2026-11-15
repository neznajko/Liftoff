////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
////////////////////////////////////////////////////////////////
import net.liftoff.Ball;
import net.liftoff.Bounds;
////////////////////////////////////////////////////////////////
public class Vaus extends Bounds
{
    private static final float SPEED = 200f;
    private static final float WIDTH = 200f;
    private static final float HEIGHT = 100f;

    private float speed;
    private float width;
    private float height;

    private Vaus( Builder builder )
    {
        super( builder.x, builder.y, builder.color );
        speed = builder.speed;
        width = builder.width;
        height = builder.height;
    }
    public static class Builder
    {
        private float x;
        private float y;
        private Color color;
        
        private float speed = SPEED;
        private float width = WIDTH;
        private float height = HEIGHT;

        public Builder( float x, float y, Color color )
        {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        public Builder speed( float speed )
        {
            this.speed = speed;
            return this;
        }
        public Builder dimensions( float width, float height )
        {
            this.width = width;
            this.height = height;
            return this;
        }
        public Vaus build()
        {
            return new Vaus( this );
        }
    }
    @Override
    public float top()
    {
        return y + height;
    }
    @Override
    public float ryte()
    {
        return x + width;
    }
    @Override
    public float bot()
    {
        return y;
    }
    @Override
    public float left()
    {
        return x;
    }
    private void keyboard()
    {
        float dt = Gdx.graphics.getDeltaTime();

        if( Gdx.input.isKeyPressed( Keys.LEFT )){
            x -= speed * dt;
            if( unbound_width()){
                x += speed * dt;
            }        
        }
        if( Gdx.input.isKeyPressed( Keys.RIGHT )){
            x += speed * dt;
            if( unbound_width()){
                x -= speed * dt;
            }        
        }
    }
    public void update( Ball ball )
    {
        keyboard();

        if( !cross( ball )){
            color = Color.BLUE;
            return;
        }
        switch( crossbar( ball )){
        case TOP:
            ball.setVy( -ball.getVy());
            ball.setY( top() + ball.getR());
            break;
        case BOT:
            ball.setVy( -ball.getVy());
            ball.setY( bot() - ball.getR());
            break;
        case LEFT:
            ball.setVx( -ball.getVx());
            ball.setX( left() - ball.getR());
            break;
        case RYTE:
            ball.setVx( -ball.getVx());
            ball.setX( ryte() + ball.getR());
            break;
        }
        color = Color.WHITE;
    }
    public void render( ShapeRenderer shape )
    {
        shape.setColor( color );
        shape.rect( x, y, width, height );
    }
}
////////////////////////////////////////////////////////////////
