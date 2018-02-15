package com.example.abimanyu.cookieclicker;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageView cookieImage;
    ImageView grandmaImage;
    ImageView farmerImage;
    ImageView minerImage;
    ImageView workerImage;
    ImageView specialImage;
    TextView grandmaDesc;
    TextView farmerDesc;
    TextView minerDesc;
    TextView workerDesc;
    TextView specialDesc;


    TextView cookieNumber;
    TextView cpsNumber;
    ConstraintLayout constraintLayout;


    volatile boolean grandmaAnimate = false;
    volatile boolean farmerAnimate = false;
    volatile boolean minerAnimate = false;
    volatile boolean workerAnimate = false;
    volatile boolean specialAnimate = false;

    volatile int lastKnownCount = 0;
    volatile int cookieCount = 0;
    volatile double cookieInterval = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookieImage = (ImageView)findViewById(R.id.id_cookieImage);
        grandmaImage = (ImageView)findViewById(R.id.grandmaImage);
        farmerImage = (ImageView)findViewById(R.id.farmerimage);
        minerImage = (ImageView)findViewById(R.id.minerImage);
        workerImage = (ImageView)findViewById(R.id.workerImage);
        specialImage = (ImageView)findViewById(R.id.specialImage);

        grandmaDesc =(TextView)findViewById(R.id.grandamDescription);
        farmerDesc = (TextView)findViewById(R.id.farmerDescription);
        minerDesc =(TextView)findViewById(R.id.minerDescription);
        workerDesc =(TextView)findViewById(R.id.workerDescription);
        specialDesc =(TextView)findViewById(R.id.specialDescription);

        cookieNumber = (TextView)findViewById(R.id.id_count);
        cpsNumber = (TextView)findViewById(R.id.id_cps);
        constraintLayout = (ConstraintLayout)findViewById(R.id.layout);

        final ScaleAnimation startAnimation = new ScaleAnimation(0.75f,1.0f,0.75f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        startAnimation.setDuration(150);

        final ScaleAnimation gaEnter = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        gaEnter.setDuration(250);

        final ScaleAnimation gaExit = new ScaleAnimation(1f,0f,1f,0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        gaExit.setDuration(250);

        Timer timer = new Timer();
        final Handler handler = new Handler();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                cookieCount += cookieInterval;
                handler.post(new Runnable() {
                    public void run(){
                        if(cookieCount>=10&&!grandmaAnimate) {grandmaImage.setEnabled(true);animateIn(grandmaDesc);animateIn(grandmaImage);grandmaAnimate=true;}
                            if(cookieCount>=20&&!farmerAnimate) {farmerImage.setEnabled(true);animateIn(farmerDesc);animateIn(farmerImage);farmerAnimate=true;}
                                if (cookieCount >= 30&&!minerAnimate) {minerImage.setEnabled(true);animateIn(minerDesc);animateIn(minerImage);minerAnimate=true;}
                                    if (cookieCount >= 40&&!workerAnimate) {workerImage.setEnabled(true);animateIn(workerDesc);animateIn(workerImage);workerAnimate=true;}
                                        if (cookieCount >= 50&&!specialAnimate) {specialImage.setEnabled(true);animateIn(specialDesc);animateIn(specialImage);specialAnimate=true;}
                        //}//else {specialImage.setVisibility(View.INVISIBLE);specialImage.setEnabled(false);}
                        //}//else{workerImage.setVisibility(View.INVISIBLE);workerImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialImage.setEnabled(false);}
                        //}//else{minerImage.setVisibility(View.INVISIBLE);minerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialImage.setEnabled(false);}
                        //}//else{farmerImage.setVisibility(View.INVISIBLE);farmerImage.setEnabled(false);minerImage.setVisibility(View.INVISIBLE);minerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialImage.setEnabled(false);}
                        //}//else{grandmaImage.setVisibility(View.INVISIBLE);grandmaImage.setEnabled(false);farmerImage.setVisibility(View.INVISIBLE);farmerImage.setEnabled(false);minerImage.setVisibility(View.INVISIBLE);minerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialImage.setEnabled(false);}

                        cookieNumber.setText(cookieChange(cookieCount));
                        cpsNumber.setText(cookieInterval +" CPS");
                    }
                });
            }
        },0, 500);

        cookieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            view.startAnimation(startAnimation);
            cookieCount++;

            addCookie(1);
            addNumber();

                if(cookieCount  >=10&&!grandmaAnimate)  {grandmaImage.setEnabled(true);animateIn(grandmaDesc);animateIn(grandmaImage);grandmaAnimate=true;}
                if(cookieCount  >=20&&!farmerAnimate)   {farmerImage.setEnabled(true);animateIn(farmerDesc);animateIn(farmerImage);farmerAnimate=true;}
                if (cookieCount >= 30&&!minerAnimate)   {minerImage.setEnabled(true);animateIn(minerDesc);animateIn(minerImage);minerAnimate=true;}
                if (cookieCount >= 40&&!workerAnimate)  {workerImage.setEnabled(true);animateIn(workerDesc);animateIn(workerImage);workerAnimate=true;}
                if (cookieCount >= 50&&!specialAnimate) {specialImage.setEnabled(true);animateIn(specialDesc);animateIn(specialImage);specialAnimate=true;}

            }
        });

        grandmaImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGrandma(1);

                if(cookieCount <10)  {grandmaImage.setEnabled(false);animateOut(grandmaDesc);animateOut(grandmaImage);grandmaAnimate=false;}
                if(cookieCount <20)  {farmerImage.setEnabled(false);farmerImage.setVisibility(View.INVISIBLE);farmerDesc.setVisibility(View.INVISIBLE);farmerAnimate=false;}
                if(cookieCount <30)  {minerImage.setEnabled(false);minerImage.setVisibility(View.INVISIBLE);minerDesc.setVisibility(View.INVISIBLE);minerAnimate=false;}
                if(cookieCount <40)  {workerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerDesc.setVisibility(View.INVISIBLE);workerAnimate=false;}
                if(cookieCount <50)  {specialImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialDesc.setVisibility(View.INVISIBLE);specialAnimate=false;}
            }
        });
        farmerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFarmer(1);
                if(cookieCount <10)  {grandmaImage.setEnabled(false);animateOut(grandmaDesc);animateOut(grandmaImage);grandmaAnimate=false;}
                if(cookieCount <20)  {farmerImage.setEnabled(false);animateOut(farmerDesc);animateOut(farmerImage);farmerAnimate=false;}
                if(cookieCount <30)  {minerImage.setEnabled(false);minerImage.setVisibility(View.INVISIBLE);minerDesc.setVisibility(View.INVISIBLE);minerAnimate=false;}
                if(cookieCount <40)  {workerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerDesc.setVisibility(View.INVISIBLE);workerAnimate=false;}
                if(cookieCount <50)  {specialImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialDesc.setVisibility(View.INVISIBLE);specialAnimate=false;}
            }
        });
        minerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMiner(1);
                if(cookieCount <10)  {grandmaImage.setEnabled(false);animateOut(grandmaDesc);animateOut(grandmaImage);grandmaAnimate=false;}
                if(cookieCount <20)  {farmerImage.setEnabled(false);animateOut(farmerDesc);animateOut(farmerImage);farmerAnimate=false;}
                if(cookieCount <30)  {minerImage.setEnabled(false);animateOut(minerDesc);animateOut(minerImage);minerAnimate=false;}
                if(cookieCount <40)  {workerImage.setEnabled(false);workerImage.setVisibility(View.INVISIBLE);workerDesc.setVisibility(View.INVISIBLE);workerAnimate=false;}
                if(cookieCount <50)  {specialImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialDesc.setVisibility(View.INVISIBLE);specialAnimate=false;}
            }
        });
        workerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorker(1);
                if(cookieCount <10)  {grandmaImage.setEnabled(false);animateOut(grandmaDesc);animateOut(grandmaImage);grandmaAnimate=false;}
                if(cookieCount <20)  {farmerImage.setEnabled(false);animateOut(farmerDesc);animateOut(farmerImage);farmerAnimate=false;}
                if(cookieCount <30)  {minerImage.setEnabled(false);animateOut(minerDesc);animateOut(minerImage);minerAnimate=false;}
                if(cookieCount <40)  {workerImage.setEnabled(false);animateOut(workerDesc);animateOut(workerImage);workerAnimate=false;}
                if(cookieCount <50)  {specialImage.setEnabled(false);specialImage.setVisibility(View.INVISIBLE);specialDesc.setVisibility(View.INVISIBLE);specialAnimate=false;}
            }
        });
        specialImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSpecial(1);
                if(cookieCount <10)  {grandmaImage.setEnabled(false);animateIn(grandmaDesc);animateOut(grandmaImage);grandmaAnimate=false;}
                if(cookieCount <20)  {farmerImage.setEnabled(false);animateOut(farmerDesc);animateOut(farmerImage);farmerAnimate=false;}
                if(cookieCount <30)  {minerImage.setEnabled(false);animateOut(minerDesc);animateOut(minerImage);minerAnimate=false;}
                if(cookieCount <40)  {workerImage.setEnabled(false);animateOut(workerDesc);animateOut(workerImage);workerAnimate=false;}
                if(cookieCount <50)  {specialImage.setEnabled(false);animateOut(specialDesc);animateOut(specialImage);specialAnimate=false;}
            }
        });

    }
    public void addGrandma(int i){
        for(int j=0;j<i;j++) {
            ImageView grandmaClone = new ImageView(this);
            grandmaClone.setId(View.generateViewId());
            grandmaClone.setImageResource(R.drawable.grandma);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
            grandmaClone.setLayoutParams(params);

            constraintLayout.addView(grandmaClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.TOP, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(grandmaClone.getId(), (float) Math.random());
            constraintSet.setHorizontalBias(grandmaClone.getId(), (float) Math.random());

            constraintSet.applyTo(constraintLayout);

            final ScaleAnimation grandmaAnimation = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            grandmaAnimation.setDuration(150);
            grandmaClone.setAnimation(grandmaAnimation);

            cookieCount-=10;
            cookieInterval+=1;
        }
    }
    public void addFarmer(int i){
        for(int j=0;j<i;j++) {
            ImageView grandmaClone = new ImageView(this);
            grandmaClone.setId(View.generateViewId());
            grandmaClone.setImageResource(R.drawable.farmer);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
            grandmaClone.setLayoutParams(params);

            constraintLayout.addView(grandmaClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.TOP, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(grandmaClone.getId(), (float) Math.random());
            constraintSet.setHorizontalBias(grandmaClone.getId(), (float) Math.random());

            constraintSet.applyTo(constraintLayout);

            final ScaleAnimation grandmaAnimation = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            grandmaAnimation.setDuration(150);
            grandmaClone.setAnimation(grandmaAnimation);

            cookieCount-=20;
            cookieInterval+=2;
        }
    }
    public void addMiner(int i){
        for(int j=0;j<i;j++) {
            ImageView grandmaClone = new ImageView(this);
            grandmaClone.setId(View.generateViewId());
            grandmaClone.setImageResource(R.drawable.miner);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
            grandmaClone.setLayoutParams(params);

            constraintLayout.addView(grandmaClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.TOP, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(grandmaClone.getId(), (float) Math.random());
            constraintSet.setHorizontalBias(grandmaClone.getId(), (float) Math.random());

            constraintSet.applyTo(constraintLayout);

            final ScaleAnimation grandmaAnimation = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            grandmaAnimation.setDuration(150);
            grandmaClone.setAnimation(grandmaAnimation);

            cookieCount-=30;
            cookieInterval+=5;
        }
    }
    public void addWorker(int i){
        for(int j=0;j<i;j++) {
            ImageView grandmaClone = new ImageView(this);
            grandmaClone.setId(View.generateViewId());
            grandmaClone.setImageResource(R.drawable.worker);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
            grandmaClone.setLayoutParams(params);

            constraintLayout.addView(grandmaClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.TOP, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(grandmaClone.getId(),(float) Math.random());
            constraintSet.setHorizontalBias(grandmaClone.getId(),(float) Math.random());

            constraintSet.applyTo(constraintLayout);

            final ScaleAnimation grandmaAnimation = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            grandmaAnimation.setDuration(150);
            grandmaClone.setAnimation(grandmaAnimation);

            cookieCount-=40;
            cookieInterval+=7;
        }
    }
    public void addSpecial(int i){
        for(int j=0;j<i;j++) {
            ImageView grandmaClone = new ImageView(this);
            grandmaClone.setId(View.generateViewId());
            grandmaClone.setImageResource(R.drawable.special);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(100,100);
            grandmaClone.setLayoutParams(params);

            constraintLayout.addView(grandmaClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.TOP, cookieImage.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(grandmaClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(grandmaClone.getId(), (float) Math.random());
            constraintSet.setHorizontalBias(grandmaClone.getId(), (float) Math.random());

            constraintSet.applyTo(constraintLayout);

            final ScaleAnimation grandmaAnimation = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            grandmaAnimation.setDuration(150);
            grandmaClone.setAnimation(grandmaAnimation);

            cookieCount-=50;
            cookieInterval+=10;
        }
    }
    public void addNumber(){
        for(int j=0;j<1;j++) {
            TextView textViewInCode;
            textViewInCode = new TextView(this);
            textViewInCode.setId(View.generateViewId());
            textViewInCode.setText("+1");
            textViewInCode.setTextSize(12);
            textViewInCode.setTextColor(Color.WHITE);
            textViewInCode.setTypeface(null, Typeface.BOLD_ITALIC);

            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT);
            textViewInCode.setLayoutParams(params);

            constraintLayout.addView(textViewInCode);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(textViewInCode.getId(),ConstraintSet.TOP,cookieImage.getId(),ConstraintSet.TOP);
            constraintSet.connect(textViewInCode.getId(),ConstraintSet.BOTTOM,cookieImage.getId(),ConstraintSet.BOTTOM);
            constraintSet.connect(textViewInCode.getId(),ConstraintSet.LEFT,cookieImage.getId(),ConstraintSet.LEFT);
            constraintSet.connect(textViewInCode.getId(),ConstraintSet.RIGHT,cookieImage.getId(),ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(textViewInCode.getId(),(float)Math.random());
            constraintSet.setHorizontalBias(textViewInCode.getId(),(float)Math.random());

            constraintSet.applyTo(constraintLayout);

            float up = -100;
            float finalFloat = textViewInCode.getY()+up;


            textViewInCode.bringToFront();

            final TranslateAnimation translateNumber= new TranslateAnimation(textViewInCode.getX(),textViewInCode.getX(),textViewInCode.getY(),finalFloat);
            translateNumber.setDuration(350);
            textViewInCode.startAnimation(translateNumber);

            textViewInCode.setVisibility(View.INVISIBLE);
        }

    }

    public void addCookie(int i){
        for(int j=0;j<i;j++) {
            ImageView cookieClone = new ImageView(this);
            cookieClone.setId(View.generateViewId());
            cookieClone.setImageResource(R.drawable.cookie);
            //defing the wrapContent for the Constraint Layout and applying it to the TextView
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(50,50);
            cookieClone.setLayoutParams(params);


            cookieNumber.bringToFront();
            cpsNumber.bringToFront();
            grandmaImage.bringToFront();
            grandmaDesc.bringToFront();
            farmerImage.bringToFront();
            farmerDesc.bringToFront();
            minerImage.bringToFront();
            minerDesc.bringToFront();
            workerImage.bringToFront();
            workerDesc.bringToFront();
            specialImage.bringToFront();
            specialDesc.bringToFront();



            constraintLayout.addView(cookieClone);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);

            //attaching the TextView to another TV...
            constraintSet.connect(cookieClone.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(cookieClone.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
            constraintSet.connect(cookieClone.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);
            constraintSet.connect(cookieClone.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);

            constraintSet.setVerticalBias(cookieClone.getId(), 0);
            constraintSet.setHorizontalBias(cookieClone.getId(), (float) Math.random());

            constraintSet.applyTo(constraintLayout);

            cookieImage.bringToFront();

            final TranslateAnimation translateNumber= new TranslateAnimation(cookieClone.getX(),cookieClone.getX(),-50,800);
            translateNumber.setDuration(1000);
            cookieClone.startAnimation(translateNumber);

            cookieClone.setVisibility(View.INVISIBLE);
        }
    }

    public void animateIn(final View view){
        final ScaleAnimation gaEnter = new ScaleAnimation(0f,1.0f,0f,1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        gaEnter.setDuration(250);

        gaEnter.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(gaEnter);
    }
    public void animateOut(final View view){
        final ScaleAnimation gaExit = new ScaleAnimation(1f,0f,1f,0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        gaExit.setDuration(250);

        gaExit.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(gaExit);
    }

    public String cookieChange(int i) {
        if(i==1)
            return i+" Cookie";
        else
            return i+" Cookies";

    }

}
