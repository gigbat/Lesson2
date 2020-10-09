public class Main {
    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        resource.setI(5);
        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();

        myThread.setName("one");

        myThread.setResource(resource);
        myThread1.setResource(resource);

        myThread.start();
        myThread1.start();

        myThread.join();
        myThread1.join();

        System.out.println(resource.getI());
    }
}

class MyThread extends Thread {
    Resource resource;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.change();
    }
}

class Resource {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public synchronized void change() {
        int i = this.i;

        if (Thread.currentThread().getName().equals("one")) {
            Thread.yield();
        }

        i++;
        this.i = i;
    }
}