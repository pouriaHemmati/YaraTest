package org.pouria.yara.mvp.details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.room.Room;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;
import org.pouria.yara.R;
import org.pouria.yara.base.BaseActivity;
import org.pouria.yara.base.BaseAppCompatActivity;
import org.pouria.yara.local.MyDataBaseDetails;
import org.pouria.yara.model.JDetails;
import org.pouria.yara.util.NetworkUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseAppCompatActivity implements IDetailsView {
    DetailsPresenter detailsPresenter;
    @BindView(R.id.txt_title_details)
    TextView txt_title_details;
    @BindView(R.id.txt_year_details)
    TextView txt_year_details;
    @BindView(R.id.txt_runtime_details)
    TextView txt_runtime_details;
    @BindView(R.id.txt_language_details)
    TextView txt_language_details;
    @BindView(R.id.txt_rate_details)
    TextView txt_rate_details;
    @BindView(R.id.txt_plot_details)
    TextView txt_plot_details;
    @BindView(R.id.txt_type_details)
    TextView txt_type_details;
    @BindView(R.id.txt_actor_details)
    TextView txt_actor_details;
    @BindView(R.id.txt_director_details)
    TextView txt_director_details;
    @BindView(R.id.txt_genre_details)
    TextView txt_genre_details;
    @BindView(R.id.txt_awards_details)
    TextView txt_awards_details;
    @BindView(R.id.txt_country_details)
    TextView txt_country_details;
    @BindView(R.id.txt_released_details)
    TextView txt_released_details;
    @BindView(R.id.txt_writer_details)
    TextView txt_writer_details;
    @BindView(R.id.txt_box_office_detail)
    TextView txt_box_office_detail;
    MyDataBaseDetails myDataBaseDetails;
    private String content;
    @BindView(R.id.img_details)
    ImageView img_details;
    @BindView(R.id.lay_nested)
    NestedScrollView lay_nested;
    Picasso picasso;
    private JDetails jDetail = new JDetails();
    @BindView(R.id.lay_notFound)
    ConstraintLayout lay_notFound;
    @BindView(R.id.app_bar)
    AppBarLayout app_bar;
    @BindView(R.id.layout_loading)
    RelativeLayout layout_loading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        picasso = Picasso.get();
        detailsPresenter = new DetailsPresenter(this, new DetailsIntractor());
        myDataBaseDetails = Room.databaseBuilder(BaseActivity.getContext(), MyDataBaseDetails.class, "DetailsDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        // get bundle
        Intent in = getIntent();
        Bundle content_search = in.getExtras();
        if (content_search != null) {
            content = content_search.getString("imdbID");
            request();
        }
    }

    // request
    private void request() {
        if (NetworkUtils.isConnected(BaseActivity.getContext())) {
            detailsPresenter.callDetails(content);
        } else {
            if (myDataBaseDetails.detailsDao().findByName(content).size() > 0) {
                lay_nested.setVisibility(View.VISIBLE);
                app_bar.setVisibility(View.VISIBLE);
                jDetail = myDataBaseDetails.detailsDao().getAllDetails(content);
                setAtt();
            } else {
                layout_loading.setVisibility(View.GONE);
                lay_nested.setVisibility(View.GONE);
                app_bar.setVisibility(View.GONE);
                Toast.makeText(BaseActivity.getContext(), "اطلاعاتی یافت نشد",
                        Toast.LENGTH_LONG).show();
                lay_notFound.setVisibility(View.VISIBLE);
            }
        }
    }

    // click
    @OnClick(R.id.btn_try)
    public void btn_try() {
        request();
        layout_loading.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.img_back)
    public void img_back() {
       finish();
    }

    // response
    @Override
    public void successDetails(JDetails jDetails) {
        myDataBaseDetails.detailsDao().delete(myDataBaseDetails.detailsDao().getAll());
        lay_notFound.setVisibility(View.GONE);
        lay_nested.setVisibility(View.VISIBLE);
        app_bar.setVisibility(View.VISIBLE);
        if (myDataBaseDetails.detailsDao().findByName(content).size() == 0) {
            myDataBaseDetails.detailsDao().setAllDetails(jDetails);
        }
        jDetail = jDetails;
        setAtt();
    }

    @Override
    public void errorDetails(String error) {
        Toast.makeText(BaseActivity.getContext(), error,
                Toast.LENGTH_LONG).show();
    }

    // set Att
    @SuppressLint("SetTextI18n")
    private void setAtt() {
        if (jDetail != null) {
            picasso.load(jDetail.Poster)
                    .into(img_details);
            txt_title_details.setText(jDetail.Title);
            txt_year_details.setText("Year : " + jDetail.Year);
            txt_runtime_details.setText("Runtime : " + jDetail.Runtime);
            txt_language_details.setText("Language : " + jDetail.Language);
            txt_rate_details.setText("imdbRating : " + jDetail.imdbRating);
            txt_plot_details.setText("Plot : " + jDetail.Plot);
            txt_actor_details.setText("Actors : " + jDetail.Actors);
            txt_director_details.setText("Director : " + jDetail.Director);
            txt_genre_details.setText("Genre : " + jDetail.Genre);
            txt_awards_details.setText("Awards : " + jDetail.Awards);
            txt_country_details.setText("Country : " + jDetail.Country);
            txt_released_details.setText("Released : " + jDetail.Released);
            txt_writer_details.setText("Writer : " + jDetail.Writer);
            txt_box_office_detail.setText( jDetail.BoxOffice);
            txt_type_details.setText("Type : " + jDetail.Type);
            layout_loading.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
