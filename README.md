# DatacomAssessmentExam

[Download From Google Drive](https://drive.google.com/file/d/1sQd7ja7_ic_SojhYhtqmHI8X3ACuYjbT/view?usp=sharing)

## Libraries Used
| Type                 | Library                  | Link                                                         |
|----------------------|--------------------------|--------------------------------------------------------------|
| Data Persistence     | Room Persistence Library | https://developer.android.com/jetpack/androidx/releases/room |
| Rest API Client      | Retrofit                 | https://square.github.io/retrofit/                           |
| Image Loader         | Glide                    | https://github.com/bumptech/glide                		         |


## Mapping Challenge:
I thought of two options to implement the mapping of the albums. I went for option one.

- Option 2
    - What I thought with option 2 is I could use the current function of the api (https://jsonplaceholder.typicode.com/users/1) to get a specific 
      user base on the userId.
    - What will happen with this is the users endpoint will be dependent with the albums endpoint.
    - I'll be iterating through the albums list with every item I'll call the user by id endpoint to get the value of the name.
    - The pro of this method is the app is more flexible. Let's say the users has more than 10 items. It won't need to reiterate through all of them.
    - The big con is there will be a lot of service call in which is not good.

- Option 1
    - What I thought with option 1 is I'll call the the getUsers endpoint first to load all the users then call the getAlbums endpoint.
    - After doing so, I'll match the albums that I got with the users to be able to get the name value.
    - The big pro with this is there's lesser service call which is better.
    - The con if this is every album item wil iterate through the users but given that I placed a break statement. Once the album finds its match
      the iteration will continue with the next one.
