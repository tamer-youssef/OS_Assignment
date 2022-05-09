#include <stdio.h>
#include <stdlib.h>

// to run this file:
// run cmd
// navigate to this program's file location
// run these commands:
//      gcc -o T6 Task_6.c
//      ./T6 19986

int main(int argc, char*argv[]){

    if (argc > 2){
        printf("Only 1 parameter is allowed to be entered.\n");
        exit(1);
    }

    unsigned long address, page, offset;

    address = atoi(argv[1]);
    page = address >> 12;
    offset = address &0xfff;

    printf("The address %lu contains: \n", address);
    printf("Page Number: %lu\n", page);
    printf("Offset: %lu\n", offset);

    return 0;
}