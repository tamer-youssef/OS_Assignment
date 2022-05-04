#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
int main()
{
    int fd_1[2];
    int fd_2[2];
    int returnval1, returnval2;

    char pipe1[20];
    char pipe2[20];
    char piperead[20];
    char piperead2[20];

    printf("Enter the sentence you want to reverse-case: ");
    scanf("%[^\n]%*c", pipe1);

    returnval1 = pipe(fd_1);
    if (returnval1 == -1) {
        printf("Unable to create pipe 1 \n");
        exit(1);
    }

    returnval2 = pipe(fd_2);
    if (returnval2 == -1) {
       printf("Unable to create pipe 2 \n");
       exit(1);
    }

    write(fd_1[1], pipe1, 20);
    read(fd_1[0], piperead, 20);
    printf("The sentence you entered is: %s\n", piperead);

    for (int i = 0; piperead[i] != '\0'; i++){
        if(piperead[i]>='a' && piperead[i]<='z') {
        piperead[i] = piperead[i] - 32;
        }
        else if(piperead[i]>='A' && piperead[i]<='Z') {
        piperead[i] = piperead[i] + 32;
        }
    }

    write(fd_2[1], piperead, 20);
    read(fd_2[0], piperead2, 20);
    printf("The reverse-case text is: %s\n",piperead);
    
    return 0;
}


