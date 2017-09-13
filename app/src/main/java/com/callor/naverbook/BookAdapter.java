package com.callor.naverbook;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.callor.naverbook.databinding.BookItemViewBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by callor on 2017-09-13.
 */

public class BookAdapter extends RecyclerView.Adapter {

    List<NaverBookVO> books;
    Context context ;

    public BookAdapter(Context context,List<NaverBookVO> books){
        this.books = books;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BookItemViewBinding itemViewBinding = BookItemViewBinding.inflate(layoutInflater,parent,false);
        return new BookHolder(itemViewBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookHolder bookHolder = (BookHolder)holder;

        String bookTitle = books.get(position).getTitle();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bookHolder.itemViewBinding.bookItemTitle
                    .setText(Html.fromHtml(bookTitle,Html.FROM_HTML_MODE_LEGACY));
        } else {
            bookHolder.itemViewBinding.bookItemTitle
                    .setText(Html.fromHtml(bookTitle));
        }

        String bookDescription = books.get(position).getDescription();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bookHolder.itemViewBinding.bookItemDescription
                    .setText(Html.fromHtml(bookDescription,Html.FROM_HTML_MODE_LEGACY));
        } else {
            bookHolder.itemViewBinding.bookItemDescription
                    .setText(Html.fromHtml(bookDescription));
        }

        // 이미지 로딩
        String imageLink = books.get(position).getImage();
        Picasso.with(bookHolder.itemView.getContext())
                .load(imageLink)
                .into(bookHolder.itemViewBinding.bookItemImage);

    }

    // return값이 0이면 recyclerView에 list가 나타나지 않는다.
    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        private final BookItemViewBinding itemViewBinding ;
        public BookHolder(BookItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;
        }
    }


}
