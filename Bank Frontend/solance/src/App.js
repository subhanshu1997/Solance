import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';

function App() {

  const [queue,setQueue] = useState(null)
  const [table, setTable] = useState(null)

  let poll = async () => {
    await fetch("http://localhost:8080/api/v1/poll", {
      method: "GET"
    }).then((response) => {
      response.json().then((result) => {
        setQueue(result)
        
      })
    })
  }

  useEffect(() => {
    if(queue){
      setTable(
        <div>
        <table class="table table-striped table-bordered">
        <thead>
          <tr>
            <th scope="col">Type</th>
            <th scope="col">Message</th>
          </tr>
        </thead>
          {queue.map(item => 
          <tbody>
          <tr>
            <td>{item.type}</td>
            <td>{item.message}</td>
          </tr>
          </tbody>
          )}
      </table>
        </div>
      )
    }else{
      setTable(null)
    }
  }, [queue])


  return (
  <div className="App">
  {table}
<button type="button" class="btn btn-primary" onClick={poll}>Poll</button>
    </div>
  );
}

export default App;
