package com.itdose.multitheme.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.IntDef;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private int orientation;
    private int space;


    @Keep
    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({Type.HORIZONTAL, Type.VERTICAL, Type.GRID})
    public @interface Type {
        int HORIZONTAL = 0;
        int VERTICAL = 1;
        int GRID = -1;
    }

    public SpaceDecoration(int space) {
        this(Type.GRID, space);
    }

    public SpaceDecoration(@Type int orientation, int space) {
        this.orientation = orientation;
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildViewHolder(view).getAdapterPosition();
        int itemCount = state.getItemCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        setSpacingForDirection(outRect, layoutManager, position, itemCount);
    }


    private void setSpacingForDirection(Rect outRect, RecyclerView.LayoutManager layoutManager, int position, int itemCount) {

        // Resolve display mode automatically
        if (orientation == -1) {
            orientation = resolveDisplayMode(layoutManager);
        }

        switch (orientation) {
            case Type.HORIZONTAL:
                outRect.left = space;
                outRect.right = position == itemCount - 1 ? space : 0;
                outRect.top = space;
                outRect.bottom = space;
                break;
            case Type.VERTICAL:
                outRect.left = space;
                outRect.right = space;
                outRect.top = space;
                outRect.bottom = position == itemCount - 1 ? space : 0;
                break;
            case Type.GRID:
                if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    int cols = gridLayoutManager.getSpanCount();
                    int rows = itemCount / cols;

                    outRect.left = space;
                    outRect.right = position % cols == cols - 1 ? space : 0;
                    outRect.top = space;
                    outRect.bottom = position / cols == rows - 1 ? space : 0;
                }
                break;
        }

    }

    private int resolveDisplayMode(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) return Type.GRID;
        if (layoutManager.canScrollHorizontally()) return Type.HORIZONTAL;
        return Type.VERTICAL;
    }
}

