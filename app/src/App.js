import './App.css';
import { ReactDOM } from 'react';
import {BrowserRouter, Routes, Route } from "react-router-dom";
import SiteHeader from './components/SiteHeader';
import Landing from './components/Landing';
import Forum from './components/Forum';
import SignIn from './components/SignIn';
import ForumHeader from './components/ForumHeader';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <header className="App-header">
          <SiteHeader />
        </header>
        <Routes>
          <Route path="/" element={<SiteHeader />} />
          <Route path="SignIn" element={<SignIn />} />
          <Route path="Forum" element={<ForumHeader />}/>
          <Route path="Landing" element={<Landing />}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
