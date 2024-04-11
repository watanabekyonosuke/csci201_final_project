import './App.css';
import NavHeader from './components/NavHeader';
import Landing from './components/Landing';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <NavHeader />
      </header>
      <Landing />
    </div>
  );
}

export default App;
