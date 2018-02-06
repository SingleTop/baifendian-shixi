#include <stdio.h>  
#include <stdlib.h>  
#include <errno.h>  
#include <sys/types.h>  
#include <linux/inotify.h>  
#include <string.h> 
#include <dirent.h>
#include <unistd.h> 
#include<time.h>
#define EVENT_SIZE  ( sizeof (struct inotify_event) )  
#define EVENT_BUF_LEN     ( 1024 * ( EVENT_SIZE + 16 ) )  
 


int  x(char ** filename); 
int main( )  
{ 
printf("what");
int i, j=0;
char **filename=NULL;
  filename = (char **)malloc(sizeof(char*)*20);
for (i = 0; i < 20; i ++) {
filename [i] = (char*)malloc(sizeof(char)*40);
}

for(i = 1; i <= 10; i++){
int wd;
char str1[40] = "/home/bmf/crawl-2.2/node";
char str2[40] = "/home/bmf/crawl-2.3/node";
char str[40];
if(i <=5){
strcpy(str, str1);

wd = i;
} else{
strcpy(str, str2);
wd = i-5;
}
int length = strlen(str);

char a[2];
sprintf(a, "%d", wd); 
strcat(str, a);
strcat(str, "/logs/");
printf ("%s \n", str);
strcpy(filename[i-1], str);



}
x(filename);
}
int x(char**  filename){
char filetrace [200][60];

FILE * modify;
modify = fopen("modify", "w");
 
    int length;  
    int i = 0;  
    int fd;  
    int wd;  
    char buffer[EVENT_BUF_LEN];  
  
    /*creating the INOTIFY instance*/  
    fd = inotify_init();  
  
    /*checking for error*/  
    if (fd < 0) {  
        perror("inotify_init");  
    }  
  
    /*adding the “/tmp” directory into watch list. Here, the suggestion is to validate the existence of the directory before adding into monitoring list.*/  
 //   wd = inotify_add_watch(fd, "./test1", IN_CREATE | IN_DELETE);  
 //   inotify_add_watch(fd, "./test2", IN_CREATE | IN_DELETE);  //添加了一个子目录进行监听，默认是不会监听子目录的  
for (i=0; i < 10; i++){

    DIR    *dir=NULL;
    struct    dirent    *ptr=NULL;
if((dir = opendir(filename[i]))==NULL)
  
    {  
  
  printf("opendir failed!");  
}else {

 
    dir = opendir(filename[i]);
    
    while((ptr = readdir(dir)) != NULL)        
if (strcmp (ptr -> d_name, ".") !=0  && strcmp (ptr -> d_name, "..")!=0 ){
 char   onefile [60];
strcpy(onefile, filename[i]);
strcat(onefile, ptr -> d_name);
 printf("file_name: %s\n", onefile);
int id;
id = inotify_add_watch(fd, onefile, IN_CREATE | IN_DELETE );
strcpy(filetrace[id], onefile);
}
    closedir(dir);
//inotify_add_watch(fd, filename[i], IN_CREATE | IN_DELETE );
}}   



 /*read to determine the event change happens on “/tmp” directory. Actually this read blocks until the change event occurs*/  
  
    for ( ; ;)  // 在外面加了一个循环，防止监听完一个事件后就退出了  
    {  
        i = 0;  
        length = read(fd, buffer, EVENT_BUF_LEN);  
  
        /*checking for error*/  
        if (length < 0) {  
            perror("read");  
        }  
  
        /*actually read return the list of change events happens. Here, read the change event one by one and process it accordingly.*/  
        while (i < length) {  
            struct inotify_event *event = (struct inotify_event *) &buffer[i];  
            if (event->len) { 

               FILE *fp = fopen("./notify_directory","a+b");
void *p = stdout;
stdout = fp;
 time_t timep;
 time (&timep);
 
                if (event->mask & IN_CREATE) {  
                    if (event->mask & IN_ISDIR) {  
                       printf("%s       New directory %s created  %s\n",ctime(&timep), event->name,filetrace[event->wd] ); 
//fprintf(modify, "New directory %s created.\n", event->name); 
                    } else {  
                       printf("%s       New file %s created       %s \n", ctime(&timep), event->name, filetrace[event->wd]); 
//fprintf(modify, "New file %s created.  wd is %d \n", event->name, event->wd); 
                    }  
                } else if (event->mask & IN_DELETE) {  
                    if (event->mask & IN_ISDIR) {  
//fprintf(modify, "Directory %s deleted.\n", event->name); 
                       printf("%s       Directory %s deleted      %s \n",ctime(&timep), event->name, filetrace[event->wd]);  
                    } else {  
//fprintf(modify, "File %s deleted.\n", event->name);                
        	       printf("%s       File %s deleted           %s \n", ctime(&timep), event->name, filetrace[event->wd]);  
                    }  
                }
fclose(fp);
stdout = p;
            }  
            i += EVENT_SIZE + event->len; 
        }  
  
    }  
    /*removing the “/tmp” directory from the watch list.*/  
    inotify_rm_watch(fd, wd);  
    /*closing the INOTIFY instance*/  
    close(fd);  
    return 0;  
}  
