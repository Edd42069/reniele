package com.example.brickbreaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private Paint paint;
    private RectF paddle;
    private RectF ball;
    private float ballXVelocity;
    private float ballYVelocity;
    private List<RectF> bricks;
    private int score;
    private boolean gameOver;
    private boolean paused;
    private List<Level> levels;
    private int currentLevel;
    private int remainingBricks;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        paddle = new RectF(400, 800, 600, 820);
        ball = new RectF(450, 780, 500, 830);
        ballXVelocity = 5;
        ballYVelocity = -5;
        score = 0;
        gameOver = false;
        paused = false;

        initLevels();
        currentLevel = 0;
        loadLevel(currentLevel);
    }

    private class Level {
        private int numBricks;
        private int ballSpeed;

        public Level(int numBricks, int ballSpeed) {
            this.numBricks = numBricks;
            this.ballSpeed = ballSpeed;
        }

        public int getNumBricks() {
            return numBricks;
        }

        public int getBallSpeed() {
            return ballSpeed;
        }
    }

    private void initLevels() {
        levels = new ArrayList<>();
        levels.add(new Level(15, 5)); // Level 1
        levels.add(new Level(20, 6)); // Level 2
        levels.add(new Level(25, 7)); // Level 3
    }

    private void loadLevel(int levelIndex) {
        Level level = levels.get(levelIndex);
        ballXVelocity = level.getBallSpeed();
        ballYVelocity = -level.getBallSpeed();
        remainingBricks = level.getNumBricks();
        bricks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < remainingBricks / 5; j++) {
                bricks.add(new RectF(100 + i * 130, 100 + j * 60, 200 + i * 130, 150 + j * 60));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        if (gameOver) {
            paint.setColor(Color.RED);
            paint.setTextSize(100);
            canvas.drawText("Game Over", 300, 400, paint);
            paint.setTextSize(50);
            canvas.drawText("Score: " + score, 350, 500, paint);
            return;
        }

        if (paused) {
            paint.setColor(Color.YELLOW);
            paint.setTextSize(100);
            canvas.drawText("Paused", 350, 400, paint);
            return;
        }

        paint.setColor(Color.WHITE);
        canvas.drawRect(paddle, paint);
        paint.setColor(Color.RED);
        canvas.drawOval(ball, paint);
        paint.setColor(Color.GREEN);
        for (RectF brick : bricks) {
            canvas.drawRect(brick, paint);
        }
        paint.setColor(Color.YELLOW);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 50, 50, paint);

        ball.left += ballXVelocity;
        ball.right += ballXVelocity;
        ball.top += ballYVelocity;
        ball.bottom += ballYVelocity;

        if ((ball.left <= 0) || (ball.right >= getWidth())) {
            ballXVelocity = -ballXVelocity;
        }
        if (ball.top <= 0) {
            ballYVelocity = -ballYVelocity;
        }
        if (ball.bottom >= getHeight()) {
            gameOver = true;
            invalidate();
            return;
        }

        if (RectF.intersects(paddle, ball)) {
            ballYVelocity = -ballYVelocity;
            ball.bottom = paddle.top;
            ball.top = ball.bottom - 50;
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (RectF.intersects(bricks.get(i), ball)) {
                ballYVelocity = -ballYVelocity;
                bricks.remove(i);
                score += 10;
                remainingBricks--;
                if (remainingBricks == 0) {
                    currentLevel++;
                    if (currentLevel < levels.size()) {
                        loadLevel(currentLevel);
                    } else {
                        gameOver = true;
                    }
                }
                break;
            }
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE && !paused) {
            paddle.left = event.getX() - 100;
            paddle.right = event.getX() + 100;
            invalidate();
        }
        return true;
    }

    public void pauseGame() {
        paused = true;
        invalidate();
    }

    public void resumeGame() {
        paused = false;
        invalidate();
    }
}
