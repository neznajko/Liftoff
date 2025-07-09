////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
////////////////////////////////////////////////////////////////
import lombok.Builder;
import lombok.Builder.Default;
import lombok.experimental.SuperBuilder;
////////////////////////////////////////////////////////////////
import net.liftoff.Ball;
import net.liftoff.Bounds;
////////////////////////////////////////////////////////////////
@SuperBuilder
public class Vaus extends Bounds
{
    @Builder.Default private float speed = 200;
    @Builder.Default private float width = 200;
    @Builder.Default private float height = 100;

    private void keyboard()
    {
        float delta = Gdx.graphics.getDeltaTime();

        if( Gdx.input.isKeyPressed( Keys.LEFT )){
          
            x -= speed * delta;
        }
        if( Gdx.input.isKeyPressed( Keys.RIGHT )){
            
            x += speed * delta;
        }
    }
    private float distance( Ball ball ){
        float halflife = width / 2;
        return ( ball.getX() - x - halflife ) / halflife;
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
    public void update( Ball ball )
    {
        keyboard();
        if( cross( ball )){
            Gdx.app.log
                ( "Collision",
                  "Ball-X: " + ball.getX() +
                  " Distance: " + distance( ball ) +
                  " Cross: " + crossbar( ball )
                  );
            switch( crossbar( ball )){
            case TOP:
                ball.setYSpeed( -ball.getYSpeed());
                if( distance( ball ) < 0 ){
                    ball.setXSpeed( -Math.abs( ball.getXSpeed()));
                } else {
                    ball.setXSpeed( Math.abs( ball.getXSpeed()));
                }
                ball.setY( top() + ball.getR());
                break;
            case BOT:
                ball.setYSpeed( -ball.getYSpeed());
                ball.setY( bot() - ball.getR());
                break;
            case LEFT:
                ball.setXSpeed( -ball.getXSpeed());
                ball.setX( left() - ball.getR());
                break;
            case RYTE:
                ball.setXSpeed( -ball.getXSpeed());
                ball.setX( ryte() + ball.getR());
                break;
            }
            color = Color.WHITE;
        } else {
            color = Color.BLUE;
        }
    }    
    public void render( ShapeRenderer shape )
    {
        shape.setColor( color );
        shape.rect( x, y, width, height );
    }
}
////////////////////////////////////////////////////////////////
