////////////////////////////////////////////////////////////////
package net.liftoff;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
import net.liftoff.Ball;
import net.liftoff.Vaus;
import net.liftoff.Background;
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
public class App extends ApplicationAdapter
{
    private Ball ball;
    private Vaus vaus;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private Background background;

    private void update()
    {
        ball.update();
        vaus.update( ball );
    }
    private void clear()
    {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    }
    @Override
    public void create()
    {
        ball = new Ball.Builder( 55, 50, Color.RED )
            .radius( 20 )
            .velocity( -5, 5 )
            .build();
        vaus = new Vaus.Builder( 40, 40, Color.RED )
            .dimensions( 200, 80 )
            .build();
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        background = new Background( "bg1.png" );
    }
    @Override
    public void render()
    {
        update();

        clear();

        batch.begin();
        background.render( batch );
        batch.end();

        shape.begin( ShapeRenderer.ShapeType.Filled );
        ball.render( shape );
        vaus.render( shape );
        shape.end();
    }
    @Override
    public void dispose()
    {
        shape.dispose();
        batch.dispose();
        background.dispose();
    }
}
////////////////////////// /   /////////////////////////////////
///////////////////////// /   //////////////////////////////////
//////////////////////// /   ///////////////////////////////////
/////////////////////// /   ////////////////////////////////////
// log: + use defaults in Vaus Constructor
//      + change velocity of ball to vector2d
//      + use builders for Vaus
//      + and Ball
//      + restrict Vaus movement outside frame
//      - check out Vaus color restoring
//      - think bout ball's bouncing

