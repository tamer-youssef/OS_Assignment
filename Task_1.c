#include <stdio.h>

const int max_pid = 200;
const int min_pid = max_pid / 2;
const int len = max_pid - min_pid;

int array_pid[100];

// creates data structure for representing pids
int allocate_map(void) {
    for (int i = 0; i < len-1; i++) {
        array_pid[i] = 0;
    }

    if (array_pid[len-1] == 0){
        printf("Array initialised.\n");
        printf("Array size: %d\n\n", len);
        return 0;
    }
    else {
        printf("Error initialising array.\n");
        return -1;
    }
}

// allocates and returns a pid
// if unsuccessful, return 1 (all pids are in use)
int allocate_pid(void) {
    for (int i = 0; i < len; i++) {
        if (array_pid[i] == 0) {
            array_pid[i] = 1;
            printf("PID is allocated to: ");
            printf("%d\n",i);
            return i;
        }
    }
    printf("Error allocating PID - array is full.\n");
    return 1;
}

// releases a pid (removes it from array)
void release_pid(int pid) {
    array_pid[pid] = 0;
}

int main() {
    allocate_map();

    int pid1 = allocate_pid();
    int pid2 = allocate_pid();

    release_pid(pid1);
    release_pid(pid2);

    return 0;
}