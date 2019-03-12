package com.refreshviewpager.viewpager2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.refreshviewpager.ListUtils;
import com.refreshviewpager.MyApplication;
import com.refreshviewpager.PagerSlidingIndicator;
import com.refreshviewpager.R;

import java.util.ArrayList;
import java.util.List;


public class BannerView extends FrameLayout {
    private ViewPager2 mViewPager;
    private PagerSlidingIndicator mIndicator;
    private Context context;
    private BannerView.BannerAdapter mMyAdapter;
    //  重新构造后的轮播数据集合
    private List<String> mListAdd;
    //  图片当前位置
    private int currentPosition;
    private List<String> mList;
    private int indicatorPostion;


    public BannerView(@NonNull Context context) {
        super(context);
        init();
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        context = getContext();
        mListAdd = new ArrayList<>();
        mList = new ArrayList<>();
        setLayout();
    }


    private void setLayout() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_optional_banner, this, true);
        mViewPager = inflate.findViewById(R.id.vp_optional);
        mIndicator = inflate.findViewById(R.id.pts_optional);
        setPageChangeListener();
    }

    public void setImags(List<String> strings) {
        mList.clear();
        mList.addAll(strings);
        createData();
        if (mMyAdapter != null) {
            mMyAdapter.addImage(mListAdd);
        }
        mIndicator.setViewPagerNoPageChanger(mViewPager);
        if (strings.size() > 1) {
            mIndicator.setVisibility(VISIBLE);
        } else {
            mIndicator.setVisibility(GONE);
        }
        mViewPager.setCurrentItem(0);
    }

    public void upDate(List<String> strings) {
        mList.clear();
        mList.addAll(strings);
        createData();
        if (mMyAdapter != null) {
            mMyAdapter.update(mListAdd);
        }
        if (strings.size() > 1) {
            mIndicator.setVisibility(VISIBLE);
        } else {
            mIndicator.setVisibility(GONE);
        }
    }


    //  ViewPager页面改变监听
    private void setPageChangeListener() {
        mMyAdapter = new BannerView.BannerAdapter(context);
        mViewPager.setAdapter(mMyAdapter);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    mIndicator.onPageScrolled(mListAdd.size() - 3, positionOffset, positionOffsetPixels);
                } else if (position >= mListAdd.size() - 2) {
                } else {
                    mIndicator.onPageScrolled(position - 1, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                pageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mViewPager.setCurrentItem(currentPosition, false);
                    mIndicator.onPageScrollStateChanged(state, indicatorPostion);
                }
            }
        });
    }

    private void pageSelected(int position) {
        if (position == 0) {    //判断当切换到第0个页面时把currentPosition设置为list.size(),即倒数第二个位置，小圆点位置为length-1
            currentPosition = mList.size();
            indicatorPostion = mList.size() - 1;
        } else if (position == mList.size() + 1) {    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
            currentPosition = 1;
            indicatorPostion = 0;
        } else {
            currentPosition = position;
            indicatorPostion = position - 1;
        }
        mIndicator.onPageSelected(currentPosition);
    }

    private void createData() {
        mListAdd.clear();
        currentPosition = 1;
        if (!ListUtils.equalsNull(mList)) {
            for (int i = 0; i < mList.size() + 2; i++) {
                if (i == 0) {   //  判断当i=0为该处的mList的最后一个数据作为mListAdd的第一个数据
                    mListAdd.add(mList.get(mList.size() - 1));
                } else if (i == mList.size() + 1) {   //  判断当i=mList.size()+1时将mList的第一个数据作为mListAdd的最后一个数据
                    mListAdd.add(mList.get(0));
                } else {  //  其他情况
                    mListAdd.add(mList.get(i - 1));
                }
            }
        }
    }

    class BannerAdapter extends RecyclerView.Adapter<BannerView.BannerAdapter.ViewHolder> {

        private List<String> list = new ArrayList<>();
        private Context context;

        public BannerAdapter(Context context) {
            this.context = context;
        }

        public void addImage(List<String> data) {
            if (!ListUtils.equalsNull(data)) {
                list.clear();
                list.addAll(data);
            }
            notifyDataSetChanged();
        }

        public void update(List<String> data) {
            if (!ListUtils.equalsNull(data)) {
                list.clear();
                list.addAll(data);
            }
            notifyItemRangeChanged(0, list.size());
        }

        @Override
        public BannerView.BannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_optional, parent, false);
            return new BannerView.BannerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BannerView.BannerAdapter.ViewHolder holder, int position) {
            setUrlImage(list.get(position), holder.imageView);
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
            }
        }

        public void setUrlImage(String url, ImageView imageView) {
            Context mContext = context != null ? context : MyApplication.getInstance();
            if (url == null || imageView == null) {
                return;
            }
            Glide.with(mContext).load(url).into(imageView);
        }
    }
}
