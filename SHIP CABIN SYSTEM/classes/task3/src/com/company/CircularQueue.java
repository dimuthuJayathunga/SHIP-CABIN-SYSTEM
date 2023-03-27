package com.company;
import java.util.ArrayList;


public class CircularQueue {
    private int size, front, rear;
    private Passenger[] queue = new Passenger[12];

    CircularQueue(int size) {
        this.size = size;
        this.front = this.rear = -1;
    }

    public Passenger[] getQueue() {
        return queue;
    }

    public void enQueue(Passenger passenger) {

        if ((front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1))) {
            System.out.print("Waiting list is Full. Sorry! We cannot add anymore passengers to the waiting list.");
        }

        else if (front == -1) {
            front = 0;
            rear = 0;
            queue[rear] = passenger;
        } else if (rear == size - 1 && front != 0) {
            rear = 0;
            queue[rear] = passenger;
        } else {
            rear = (rear + 1);

            if (front <= rear) {
                queue[rear] = passenger;
            }

            else {
                queue[rear] = passenger;
            }
        }
    }

    public Passenger deQueue() {
        Passenger deQueuedPassenger;

        if (front == -1) {
            return null;
        }

        deQueuedPassenger = queue[front];

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front += 1;
        }

        return deQueuedPassenger;
    }
}

