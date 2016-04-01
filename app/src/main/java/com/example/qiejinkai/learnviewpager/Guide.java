package com.example.qiejinkai.learnviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiejinkai on 16/4/1.
 */
public class Guide extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager vp ;
    private MyViewPagerAdapter mpa;
    private List<View> list;
    private LinearLayout ll ;

    private List<ImageView> docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        initItem();

        vp = (ViewPager) findViewById(R.id.vp);

        mpa = new MyViewPagerAdapter(list,this);

        vp.setAdapter(mpa);

        vp.addOnPageChangeListener(this);

    }

    private void initItem(){
        list = new ArrayList<View>();
        list.add(createImageView(R.drawable.guide_1,this,false));
        list.add(createImageView(R.drawable.guide_2,this,false));
        list.add(createImageView(R.drawable.guide_3,this,true));
        ll = (LinearLayout)findViewById(R.id.ll);
        docs = new ArrayList<ImageView>();
        for (int i = 0 ;i <list.size();i++){

            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.login_point);
            ll.addView(iv, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            docs.add(iv);
        }

    }

    private View createImageView(int image,Context context,boolean started){

        if(!started) {

            LinearLayout root = new LinearLayout(context);

            ImageView iv = new ImageView(context);
            iv.setImageResource(image);

            root.addView(iv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            return root;
        }else{
            RelativeLayout root = new RelativeLayout(this);
            ImageView iv = new ImageView(this);
            iv.setImageResource(image);
            root.addView(iv, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

            LinearLayout child = new LinearLayout(this);
            child.setGravity(Gravity.CENTER_HORIZONTAL);
            child.setOrientation(LinearLayout.HORIZONTAL);


            Button start = new Button(this);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Guide.this,MainActivity.class));
                }
            });
            start.setText("开启神秘之旅");
            child.addView(start, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            RelativeLayout.LayoutParams lp1 =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            root.addView(child,lp1);
            return root;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        int index = 0;
        for (ImageView iv:docs) {
            if(index == position){
                iv.setImageResource(R.drawable.login_point_selected);
            }else{
                iv.setImageResource(R.drawable.login_point);
            }
            index++;
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyViewPagerAdapter extends PagerAdapter{

        private List<View> views;
        private Context context;

        public MyViewPagerAdapter(List<View> views, Context context) {
            this.views = views;
            this.context = context;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
