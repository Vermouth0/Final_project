package com.swufe.wy.slimming;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialog {

    public void Dialog(){

    }
    public void showProgressDialog(Context context) {
        /* @setProgress 设置初始进度
         * @setProgressStyle 设置样式（水平进度条）
         * @setMax 设置进度最大值
         */
        final int MAX_PROGRESS = 100;
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgress(0);
        progressDialog.setTitle("玩命加载中O(∩_∩)O~~");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        /* 模拟进度增加的过程
         * 新开一个线程，每个10ms，进度增加1
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(18);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }
}
