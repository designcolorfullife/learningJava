package com.zhangwei.learning.mutithread;

import com.zhangwei.learning.utils.LoggerUtils;

public class NumberVisable {

	private static int number = 0;
	private static boolean ready = false;

	private static class NewThread extends Thread {
		@Override
		public void run() {

			//
			while (!ready)
				Thread.yield();
			LoggerUtils.Info("现在的值是" + NumberVisable.number
					+ this.getThreadGroup().getParent().getName());

		}
	}

	public static void main(String[] args) throws InterruptedException {
		int count = 9;
		while ((count--) > 0) {
			NewThread newThread = new NewThread();
			newThread.start();
			number++;
			ready = !ready;
			LoggerUtils.Info("当前count" + count);
		}
	}
}
