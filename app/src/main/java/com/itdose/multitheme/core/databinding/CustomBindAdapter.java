package com.itdose.multitheme.core.databinding;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.constraintlayout.widget.Group;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.itdose.multitheme.data.remote.lib.Resource;
import com.itdose.multitheme.data.remote.lib.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomBindAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, int resource) {
        view.setBackgroundResource(resource);
    }


    @BindingAdapter("progressResource")
    public static void setProgressResource(ProgressBar view, Resource resource) {
        int visibility = resource == null ? View.GONE : resource.getStatus() == Status.LOADING ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    @BindingAdapter("errorResource")
    public static void setErrorResource(TextView view, Resource resource) {
        int visibility = resource == null ? View.GONE : resource.getStatus() == Status.ERROR ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    @BindingAdapter("recyclerDataResource")
    public static void setDataResource(RecyclerView view, Resource resource) {
        int visibility = resource == null ? View.GONE : resource.getStatus() == Status.SUCCESS ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    // login xml
    @BindingAdapter("buttonResource")
    public static void setButtonResource(Button view, Resource resource) {
        int visibility = resource == null ? View.VISIBLE : resource.getStatus() == Status.LOADING ? View.GONE : View.VISIBLE;
        view.setVisibility(visibility);
    }

    @BindingAdapter("firstLoadGroupResource")
    public static void setFirstLoadGroupResource(Group view, Resource resource) {
        int visibility = resource == null ? View.GONE : resource.getStatus() == Status.LOADING ? View.GONE : View.VISIBLE;
        view.setVisibility(visibility);
    }





    @BindingAdapter("imageStringUrl")
    public static void loadImage(ImageView view, String url) {
        RequestOptions requestOptions = new RequestOptions().circleCrop();
        Glide.with(view.getContext()).load(url).apply(requestOptions).into(view);
    }


    @BindingAdapter("convert_time")
    public static void convertTimeStampToDate(TextView textView, String timeStamp) {

        String newDate = "";
        if (timeStamp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault());
            try {
                Date d = dateFormat.parse(timeStamp);
                SimpleDateFormat timeFormat = new SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault());
                newDate = timeFormat.format(d);
            } catch (ParseException e) {
                newDate = "";
            }
        }
        textView.setText(newDate);
    }


    @BindingAdapter("character_from_words")
    public static void firstCharFromWords(TextView textView, String words) {
        Pattern p = Pattern.compile("\\b[a-zA-Z]");
        Matcher m = p.matcher(words);
        StringBuffer newWord = new StringBuffer();
        while (m.find()) {
            newWord.append(m.group());
        }
        textView.setText(newWord);
    }


    @BindingAdapter(value = {"completeText", "highLightText", "hightLightTextColor"})
    public static void setHighLightText(TextView textView, String completeText, String highLightedText, @ColorInt int colorCode) {
        int endIndex = completeText.length();
        int fromIndex = endIndex - highLightedText.length();
        Spannable spannableString = new SpannableString(completeText);
        spannableString.setSpan(new ForegroundColorSpan(colorCode), fromIndex, endIndex, 0);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), fromIndex, endIndex, 0);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
