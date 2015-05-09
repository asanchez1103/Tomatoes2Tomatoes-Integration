package t2t.tomatoes2tomatoes;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnDragListener;
import android.widget.ImageButton;
import android.view.MotionEvent;

import java.io.IOException;
import java.lang.reflect.Method;
import android.os.Handler;
import java.util.Random;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class BoardActivity extends Activity implements OnDragListener, OnLongClickListener, Animation.AnimationListener {

    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Bundle extras = getIntent().getExtras();
        String val = extras.getString("playerNum");

        // Number of players, obtained from PlayerSelect
        int playerNum = Integer.parseInt(val);

        // if 2 players, remove third nameplate
        if (playerNum == 2) {
            ImageView cardToBeReplaced = (ImageView) findViewById(R.id.player3);
            cardToBeReplaced.setVisibility(View.INVISIBLE);
        }

        LinearLayout parentLayout=(LinearLayout)findViewById(R.id.playerHand); //activity_main layout


        LayoutInflater layoutInflater = getLayoutInflater();
        View view;


        // Register long click listener for all cards
        findViewById(R.id.card0).setOnLongClickListener(this);
        findViewById(R.id.card1).setOnLongClickListener(this);
        findViewById(R.id.card2).setOnLongClickListener(this);
        findViewById(R.id.card3).setOnLongClickListener(this);
        findViewById(R.id.card4).setOnLongClickListener(this);
        findViewById(R.id.card5).setOnLongClickListener(this);
        findViewById(R.id.card6).setOnLongClickListener(this);


        // Register drag event listeners for target layout containers
        findViewById(R.id.playerHand).setOnDragListener(this);
        findViewById(R.id.MiddleRight).setOnDragListener(this);

        // Set up animations for cards
        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        animation1.setAnimationListener(this);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        animation2.setAnimationListener(this);

        // After 2 seconds, flip cards
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StartFlip();
            }
        }, 2000);
    }


    public void StartFlip() {

        ((RelativeLayout)findViewById(R.id.card0)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card0)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card0)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card1)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card1)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card1)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card2)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card2)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card2)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card3)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card3)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card3)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card4)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card4)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card4)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card5)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card5)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card5)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.card6)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.card6)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.card6)).startAnimation(animation1);

        ((RelativeLayout)findViewById(R.id.greencard)).clearAnimation();
        ((RelativeLayout)findViewById(R.id.greencard)).setAnimation(animation1);
        ((RelativeLayout)findViewById(R.id.greencard)).startAnimation(animation1);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation==animation1) {
            if (isBackOfCardShowing) {
                ((ImageView)findViewById(R.id.card0Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card1Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card2Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card3Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card4Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card5Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.card6Img)).setImageResource(R.drawable.redcard);
                ((ImageView)findViewById(R.id.greencardImg)).setImageResource(R.drawable.greencard);
            } else {
                ((ImageView)findViewById(R.id.card0Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card1Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card2Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card3Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card4Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card5Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.card6Img)).setImageResource(R.drawable.cardback);
                ((ImageView)findViewById(R.id.greencardImg)).setImageResource(R.drawable.cardback);

            }
            ((ImageView)findViewById(R.id.card0Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card0Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card0Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card1Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card1Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card1Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card2Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card2Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card2Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card3Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card3Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card3Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card4Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card4Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card4Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card5Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card5Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card5Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.card6Img)).clearAnimation();
            ((ImageView)findViewById(R.id.card6Img)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.card6Img)).startAnimation(animation2);

            ((ImageView)findViewById(R.id.greencardImg)).clearAnimation();
            ((ImageView)findViewById(R.id.greencardImg)).setAnimation(animation2);
            ((ImageView)findViewById(R.id.greencardImg)).startAnimation(animation2);

        } else {
            isBackOfCardShowing=!isBackOfCardShowing;
            findViewById(R.id.card0).setEnabled(true);
            findViewById(R.id.card1).setEnabled(true);
            findViewById(R.id.card2).setEnabled(true);
            findViewById(R.id.card3).setEnabled(true);
            findViewById(R.id.card4).setEnabled(true);
            findViewById(R.id.card5).setEnabled(true);
            findViewById(R.id.card6).setEnabled(true);
            findViewById(R.id.greencard).setEnabled(true);
        }

        generateCards();
    }

    public void generateCards() {
        // trying to add text AFTER animation
        // ADDED FROM TRYCARDS - ALEX
        int numRedCards = 777;
        int numGreenCards = 249;
        int cardID = 10; // ID's start at 10 to avoid confusing with holder. Red cards have ID < 1000 Green cards have ID > 1000

        ReadWithScanner redParse = new ReadWithScanner(this, R.raw.redapples,1,"","",0,false);
        ReadWithScanner greenParse = new ReadWithScanner(this, R.raw.greenappples,1,"","",0,false);

        CardClass redCards[] = new CardClass[numRedCards];
        CardClass greenCards[] = new CardClass[numGreenCards];

        String nameInfoR[][] = new String[numRedCards][2];
        String nameInfoG[][] = new String[numGreenCards][2];


        try {
            nameInfoR = redParse.processLineByLine(nameInfoR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i< redCards.length; i++){
            redCards[i] = new CardClass();
            redCards[i].setID(cardID);
            redCards[i].setName(nameInfoR[i][0]);
            redCards[i].setInfo(nameInfoR[i][1]);
            redCards[i].setHolder(0);
            redCards[i].setFlip(false);
            System.out.print("Red Card #" + Integer.toString(i+1) + ":    ID: " + redCards[i].cardID +  "\nNAME: " + redCards[i].cardName + "\nDESC: " + redCards[i].cardInfo +"\n");
            cardID++;
        }
        try {
            nameInfoG = greenParse.processLineByLine(nameInfoG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardID = 1000; //green card ID start at 1000
        for(int j =0; j<greenCards.length; j++){
            greenCards[j] = new CardClass();
            greenCards[j].setID(cardID);
            greenCards[j].setName(nameInfoG[j][0]);
            greenCards[j].setInfo(nameInfoG[j][1]);
            greenCards[j].setHolder(0);
            greenCards[j].setFlip(false);
            System.out.print("Green Card # " + Integer.toString(j+1) +":    ID: " + greenCards[j].cardID + " \nNAME: " + greenCards[j].cardName + "\nDESC: " + greenCards[j].cardInfo +"\n");
            cardID++;
        }
        System.out.print("Done.");
        // end parsing

        final TypedArray Labels = getResources().obtainTypedArray(R.array.card_label);
        final TypedArray Desc = getResources().obtainTypedArray(R.array.card_desc);

        Random randomGen = new Random();
        int randomIndex = randomGen.nextInt(greenCards.length);

        TextView greenLabel = (TextView) findViewById(R.id.greencardlabel);
        greenLabel.setText(greenCards[randomIndex].cardName);
        greenLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        greenLabel.setGravity(Gravity.LEFT);
        greenLabel.setPadding(0,60,0,0);

        TextView greenDesc = (TextView) findViewById(R.id.greencarddesc);
        greenDesc.setText(greenCards[randomIndex].cardInfo);
        greenDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        greenDesc.setGravity(Gravity.RIGHT);
        greenDesc.setPadding(24,0,10,0);

        for (int i = 0; i < Desc.length(); i++){
            // add text layout to parent

            //view = layoutInflater.inflate(R.layout.single_card_layout, parentLayout, false);
            // RelativeLayout relativeLayout = (RelativeLayout)view.findViewById((R.id.cardtemplate));

            TextView labelTextView = (TextView) findViewById(Labels.getResourceId(i, -1));
            labelTextView.setText(redCards[i].cardName);
            labelTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            labelTextView.setGravity(Gravity.LEFT);
            labelTextView.setPadding(0,60,0,0);



            //parentLayout.addView(labelTextView);

            //view = layoutInflater.inflate(R.layout.card_desc, parentLayout, false);
            TextView descTextView = (TextView) findViewById(Desc.getResourceId(i, -1));
            descTextView.setText(redCards[i].cardInfo);
            descTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            descTextView.setGravity(Gravity.RIGHT);
            descTextView.setPadding(24,0,10,0);
            // parentLayout.addView(descTextView);

            //parentLayout.addView(relativeLayout);
        }

        // End of TryCards portion
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // automatically generated, leave it be
    }

    // When one of the cards are touched AND held, this is called
    @Override
    public boolean onLongClick(View imageView) {
        // Card has been touched
        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imageView);

        imageView.startDrag(clipData, shadowBuilder, imageView, 0);
        imageView.setVisibility(View.INVISIBLE);
        return true;
    }

    @Override
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        View draggedImageView = (View) dragEvent.getLocalState();

        // Handles each of the expected events
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    // Nothing here, leave

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
//                the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
//                the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:

                // This switch statement determines what happens when a card is dragged to
                // the placeholder. In the case of card 1, it replaces the placeholder and
                // disappears afterwards. Copy and paste for all cards.
                ViewGroup draggedImageViewParentLayout
                        = (ViewGroup) draggedImageView.getParent();
                draggedImageViewParentLayout.removeView(draggedImageView);
                LinearLayout bottomLinearLayout = (LinearLayout) receivingLayoutView;
                bottomLinearLayout.addView(draggedImageView);
                draggedImageView.setVisibility(View.VISIBLE);

                switch (draggedImageView.getId()) {
                    case R.id.card0:
                        CardEvent(0);
                        return true;
                    case R.id.card1:
                        CardEvent(1);
                        return true;
                    case R.id.card2:
                        CardEvent(2);
                        return true;
                    case R.id.card3:
                        CardEvent(3);
                        return true;
                    case R.id.card4:
                        CardEvent(4);
                        return true;
                    case R.id.card5:
                        CardEvent(5);
                        return true;
                    case R.id.card6:
                        CardEvent(6);
                        return true;
                    default:
                        return false;
                }

            case DragEvent.ACTION_DRAG_ENDED:
                if (!dragEvent.getResult()) {
                    draggedImageView.setVisibility(View.VISIBLE);
                }
                return true;
            default:
                break;
        }
        return false;
    }

    public void CardEvent(int id) {

        final TypedArray Labels = getResources().obtainTypedArray(R.array.card_label);
        final TypedArray Desc = getResources().obtainTypedArray(R.array.card_desc);
        final TypedArray Img = getResources().obtainTypedArray(R.array.card_img);
        final TypedArray Layout = getResources().obtainTypedArray(R.array.card_layout);

        // Replace placeholder image
        ImageView cardToBeReplaced = (ImageView) findViewById(R.id.emptycardImg);
        cardToBeReplaced.setImageDrawable(getResources().getDrawable(R.drawable.redcard));

        TextView cardLabel = (TextView) findViewById(Labels.getResourceId(id, -1));
        TextView labelTextView = (TextView) findViewById(R.id.emptycardlabel);
        labelTextView.setText(cardLabel.getText().toString());
        labelTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        labelTextView.setGravity(Gravity.LEFT);
        labelTextView.setPadding(0,60,0,0);


        TextView cardDesc = (TextView) findViewById(Desc.getResourceId(id, -1));
        TextView descTextView = (TextView) findViewById(R.id.emptycarddesc);
        descTextView.setText(cardDesc.getText().toString());
        descTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        descTextView.setGravity(Gravity.RIGHT);
        descTextView.setPadding(24,0,10,0);


        // Delete played card from deck
        RelativeLayout deleteCard = (RelativeLayout) findViewById(Layout.getResourceId(id, -1));
        deleteCard.setVisibility(View.GONE);
    }
}
