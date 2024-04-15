import './App.css';
import { ReactDOM } from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import SiteHeader from './components/SiteHeader';
import Landing from './components/Landing';
import Forum from './components/Forum';
import ForumHeader from './components/ForumHeader';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <header className="App-header">
          <SiteHeader />
        </header>
        <div className="Content">
          <Landing />
        </div>
      </div>
    </BrowserRouter>
  );
}
export default App;
