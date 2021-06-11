package com.shadt.iptv_qx.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;


import com.shadt.iptv_qx.R;


public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {
		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View contentView;
		private OnClickListener positiveButtonClickListener;
		private OnClickListener negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * Set the Dialog message from resource
		 *
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 *
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 *
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 *
		 * @param positiveButtonText
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText,
				OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText,
				OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		public CustomDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context,
					R.style.Dialog);
			View layout = inflater.inflate(R.layout.dialog_exit, null);
			dialog.addContentView(layout, new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
			// set the dialog title
//			((TextView) layout.findViewById(R.id.title)).setText(title);
			// set the confirm button
			if (positiveButtonText != null) {
				Button button1= layout.findViewById(R.id.exit);
				button1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
							@Override
							public void onFocusChange(View view, boolean b) {
								AnimationSet animationSet = new AnimationSet(true);
								ScaleAnimation scaleAnimation = new ScaleAnimation(
										1.0f, 1.2f, 1.0f, 1.2f,
										Animation.RELATIVE_TO_SELF, 0.5f,
										Animation.RELATIVE_TO_SELF, 0.5f);
								if (b){

									scaleAnimation.setDuration(500);//动画持续时间
									animationSet.addAnimation(scaleAnimation);//保存动画效果到。。
									animationSet.setFillAfter(true);//结束后保存状态
									view.startAnimation(animationSet);//设置给控件
								}else{
									animationSet.setFillAfter(false);//结束后不保存状态
									animationSet.cancel();//关闭动画
									animationSet.reset();//重置AnimationSet
								}
							}
						});
				button1.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.exit))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.sure).setVisibility(View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				Button button2 =layout.findViewById(R.id.cancel);
				button2.requestFocus();
				button2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
					@Override
					public void onFocusChange(View view, boolean b) {
						AnimationSet animationSet = new AnimationSet(true);
						ScaleAnimation scaleAnimation = new ScaleAnimation(
								1.0f, 1.2f, 1.0f, 1.2f,
								Animation.RELATIVE_TO_SELF, 0.5f,
								Animation.RELATIVE_TO_SELF, 0.5f);
						if (b){

							scaleAnimation.setDuration(500);//动画持续时间
							animationSet.addAnimation(scaleAnimation);//保存动画效果到。。
							animationSet.setFillAfter(true);//结束后保存状态
							view.startAnimation(animationSet);//设置给控件
						}else{
							animationSet.setFillAfter(false);//结束后不保存状态
							animationSet.cancel();//关闭动画
							animationSet.reset();//重置AnimationSet
						}
					}
				});
 				button2.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.cancel))
							.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.cancel).setVisibility(View.GONE);
			}
			// set the content message
			if (message != null) {
//				((TextView) layout.findViewById(R.id.tv_content))
//						.setText(message);
			} else if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((LinearLayout) layout.findViewById(R.id.content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(
						contentView, new LayoutParams(
								android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
								android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}
	}
}